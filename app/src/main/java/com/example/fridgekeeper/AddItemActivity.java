package com.example.fridgekeeper;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.*;
import com.example.fridgekeeper.model.Item;
import com.example.fridgekeeper.network.ItemApi;
import com.example.fridgekeeper.network.RetrofitClient;
import java.util.*;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddItemActivity extends AppCompatActivity {

    private EditText nameEditText;
    private Spinner categorySpinner;
    private Button btnAddCategory, btnPickDate, btnSave, btnPlus, btnMinus;
    private TextView tvExpiration, tvQuantity;

    private List<String> categories = new ArrayList<>();
    private ArrayAdapter<String> categoryAdapter;
    private int quantity = 1;
    private String selectedDate = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        nameEditText = findViewById(R.id.et_name);
        categorySpinner = findViewById(R.id.spinner_category);
        btnAddCategory = findViewById(R.id.btn_add_category);
        btnPickDate = findViewById(R.id.btn_pick_date);
        btnSave = findViewById(R.id.btn_save);
        btnPlus = findViewById(R.id.btn_plus);
        btnMinus = findViewById(R.id.btn_minus);
        tvQuantity = findViewById(R.id.tv_quantity);
        tvExpiration = findViewById(R.id.tv_expiration);

        // 기본 카테고리 초기화
        categories.addAll(Arrays.asList("음료", "육류", "과일", "채소", "유제품", "기타"));
        categoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categories);
        categorySpinner.setAdapter(categoryAdapter);

        // 카테고리 추가 버튼
        btnAddCategory.setOnClickListener(v -> {
            EditText input = new EditText(this);
            new android.app.AlertDialog.Builder(this)
                    .setTitle("새 카테고리 추가")
                    .setView(input)
                    .setPositiveButton("추가", (dialog, which) -> {
                        String newCategory = input.getText().toString().trim();
                        if (!newCategory.isEmpty() && !categories.contains(newCategory)) {
                            categories.add(newCategory);
                            categoryAdapter.notifyDataSetChanged();
                            categorySpinner.setSelection(categories.size() - 1);
                        }
                    })
                    .setNegativeButton("취소", null)
                    .show();
        });

        // 날짜 선택
        btnPickDate.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            DatePickerDialog dialog = new DatePickerDialog(
                    this,
                    (view, year, month, day) -> {
                        selectedDate = year + "-" + (month + 1) + "-" + day;
                        tvExpiration.setText("유통기한: " + selectedDate);
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
            );
            dialog.show();
        });

        // 수량 조절
        btnPlus.setOnClickListener(v -> {
            quantity++;
            tvQuantity.setText(String.valueOf(quantity));
        });

        btnMinus.setOnClickListener(v -> {
            if (quantity > 1) {
                quantity--;
                tvQuantity.setText(String.valueOf(quantity));
            }
        });

        // 저장 버튼
        btnSave.setOnClickListener(v -> saveItem());
    }

    private void saveItem() {
        String name = nameEditText.getText().toString().trim();
        String category = categorySpinner.getSelectedItem().toString();

        if (name.isEmpty() || selectedDate.isEmpty()) {
            Toast.makeText(this, "이름과 유통기한은 필수입니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        Item item = new Item();
        item.setName(name);
        item.setCategory(category);
        item.setExpirationDate(selectedDate);
        item.setQuantity(quantity);

        ItemApi api = RetrofitClient.getClient().create(ItemApi.class);
        Call<Void> call = api.addItem(item);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(AddItemActivity.this, "등록 완료!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(AddItemActivity.this, "등록 실패 (" + response.code() + ")", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(AddItemActivity.this, "서버 연결 실패", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
