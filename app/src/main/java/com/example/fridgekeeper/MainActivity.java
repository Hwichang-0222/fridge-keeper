package com.example.fridgekeeper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;

import com.example.fridgekeeper.adapter.ItemAdapter;
import com.example.fridgekeeper.model.Item;
import com.example.fridgekeeper.network.ItemApi;
import com.example.fridgekeeper.network.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ItemAdapter adapter;
    private List<Item> itemList = new ArrayList<>();
    private static final String TAG = "FridgeKeeperApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ItemAdapter(itemList);
        recyclerView.setAdapter(adapter);

        loadItems();
    }

    private void loadItems() {
        ItemApi api = RetrofitClient.getClient().create(ItemApi.class);
        api.getItems().enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    itemList.clear();
                    itemList.addAll(response.body());
                    adapter.notifyDataSetChanged();
                } else {
                    Log.e(TAG, "응답 실패 코드: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                Log.e(TAG, "통신 오류: " + t.getMessage());
            }
        });
    }
}
