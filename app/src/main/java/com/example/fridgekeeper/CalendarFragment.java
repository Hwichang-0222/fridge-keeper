package com.example.fridgekeeper;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.*;
import android.widget.Toast;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.example.fridgekeeper.model.Item;
import com.example.fridgekeeper.network.ItemApi;
import com.example.fridgekeeper.network.RetrofitClient;
import java.util.*;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CalendarFragment extends Fragment {

    private MaterialCalendarView calendarView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        calendarView = view.findViewById(R.id.calendarView);

        loadItems();
        return view;
    }

    private void loadItems() {
        ItemApi api = RetrofitClient.getClient().create(ItemApi.class);
        api.getItems().enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    for (Item item : response.body()) {
                        try {
                            String[] parts = item.getExpirationDate().split("-");
                            int year = Integer.parseInt(parts[0]);
                            int month = Integer.parseInt(parts[1]);
                            int day = Integer.parseInt(parts[2]);
                            calendarView.setDateSelected(CalendarDay.from(year, month - 1, day), true);
                        } catch (Exception ignored) {}
                    }
                } else {
                    Toast.makeText(getContext(), "데이터 불러오기 실패", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                Toast.makeText(getContext(), "서버 연결 실패", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
