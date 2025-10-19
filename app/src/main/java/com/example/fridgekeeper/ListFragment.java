package com.example.fridgekeeper;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.util.Log;
import com.example.fridgekeeper.adapter.ItemAdapter;
import com.example.fridgekeeper.model.Item;
import com.example.fridgekeeper.network.ItemApi;
import com.example.fridgekeeper.network.RetrofitClient;
import java.util.*;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListFragment extends Fragment {

    private RecyclerView recyclerView;
    private ItemAdapter adapter;
    private List<Item> itemList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new ItemAdapter(itemList);
        recyclerView.setAdapter(adapter);

        loadItems();
        return view;
    }

    private void loadItems() {
        ItemApi api = RetrofitClient.getClient().create(ItemApi.class);
        api.getItems().enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    itemList.clear();
                    itemList.addAll(response.body());

                    // ✅ 유통기한 임박 순으로 정렬
                    itemList.sort(Comparator.comparing(Item::getExpirationDate));

                    adapter.notifyDataSetChanged();
                } else {
                    Log.e("ListFragment", "응답 실패 코드: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                Log.e("ListFragment", "통신 오류: " + t.getMessage());
            }
        });
    }
}
