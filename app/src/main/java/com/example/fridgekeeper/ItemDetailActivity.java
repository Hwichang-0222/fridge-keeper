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

public class ItemDetailActivity extends AppCompatActivity {

    private EditText etName;
    private Spinner spinnerCategory;
    private TextView tvExpiration, tvQuantity;
    private Button btnPickDate, btnPlus, btnMinus, btnEdit, btnDelete, btnBack;

    private boolean isEditMode = false;
    private long itemId;
    private int quantity = 1;
    private String selectedDate = "";
    private List<String> categories = new ArrayList<>(Arrays.asList("음료", "육류", "과일", "채소", "유제품", "기타"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        etName = findViewById(R.id.et_name);
        spinnerCategory = findViewById(R.id.spinner_category);
        tvExpiration = findViewById(R.id.tv_expiration);
        tvQuantity = findViewById(R.id.tv_quantity);
        btnPickDate = findViewById(R.id.btn_pick_date);
        btnPlus = findViewById(R.id.btn_plus);
        btnMinus = findViewById(R.id.btn_minus);
        btnEdit = findViewById(R.id.btn_edit);
        btnDelete = findViewById(R.id.btn_delete);
        btnBack = findViewById(R.id.btn_back);

        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categories);
        spinnerCategory.setAdapter(categoryAdapter);

        itemId = getIntent().getLongExtra("itemId", -1);

        // 데이터 로드
        loadItem(itemId);

        // 버튼 이벤트
        btnPickDate.setOnClickListener(v -> {
            if (!isEditMode) return;
            Calendar calendar = Calendar.getInstance();
            DatePickerDialog dialog = new DatePickerDialog(this, (view, year, month, day) -> {
                selectedDate = year + "-" + (month + 1) + "-" + day;
                tvExpiration.setText("유통기한: " + selectedDate);
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            dialog.show();
        });

        btnPlus.setOnClickListener(v -> {
            if (isEditMode) {
                quantity++;
                tvQuantity.setText(String.valueOf(quantity));
            }
        });

        btnMinus.setOnClickListener(v -> {
            if (isEditMode && quantity > 1) {
                quantity--;
                tvQuantity.setText(String.valueOf(quantity));
            }
        });

        btnEdit.setOnClickListener(v -> toggleEditMode());
        btnDelete.setOnClickListener(v -> deleteItem());
        btnBack.setOnClickListener(v -> finish());
    }

    private void loadItem(long id) {
        ItemApi api = RetrofitClient.getClient().create(ItemApi.class);
        api.getItem(id).enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Item item = response.body();
                    etName.setText(item.getName());
                    spinnerCategory.setSelection(categories.indexOf(item.getCategory()));
                    tvExpiration.setText("유통기한: " + item.getExpirationDate());
                    selectedDate = item.getExpirationDate();
                    tvQuantity.setText(String.valueOf(item.getQuantity()));
                    quantity = item.getQuantity();
                    setEditMode(false); // 초기에는 보기 모드
                } else {
                    Toast.makeText(ItemDetailActivity.this, "데이터 불러오기 실패", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                Toast.makeText(ItemDetailActivity.this, "서버 연결 실패", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void toggleEditMode() {
        if (isEditMode) {
            updateItem(); // 수정 완료
        } else {
            setEditMode(true); // 수정 모드 진입
        }
    }

    private void setEditMode(boolean enable) {
        isEditMode = enable;

        etName.setEnabled(enable);
        spinnerCategory.setEnabled(enable);
        btnPickDate.setEnabled(enable);
        btnPlus.setEnabled(enable);
        btnMinus.setEnabled(enable);

        if (enable) {
            btnEdit.setText("완료");
            btnDelete.setVisibility(Button.GONE);
        } else {
            btnEdit.setText("수정");
            btnDelete.setVisibility(Button.VISIBLE);
        }
    }

    private void updateItem() {
        String name = etName.getText().toString().trim();
        String category = spinnerCategory.getSelectedItem().toString();

        if (name.isEmpty() || selectedDate.isEmpty()) {
            Toast.makeText(this, "이름과 유통기한은 필수입니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        Item updated = new Item();
        updated.setId(itemId);
        updated.setName(name);
        updated.setCategory(category);
        updated.setExpirationDate(selectedDate);
        updated.setQuantity(quantity);

        ItemApi api = RetrofitClient.getClient().create(ItemApi.class);
        api.updateItem(itemId, updated).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(ItemDetailActivity.this, "수정 완료!", Toast.LENGTH_SHORT).show();
                    setEditMode(false);
                } else {
                    Toast.makeText(ItemDetailActivity.this, "수정 실패", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(ItemDetailActivity.this, "서버 오류", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteItem() {
        ItemApi api = RetrofitClient.getClient().create(ItemApi.class);
        api.deleteItem(itemId).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(ItemDetailActivity.this, "삭제 완료!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(ItemDetailActivity.this, "삭제 실패", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(ItemDetailActivity.this, "서버 연결 실패", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
