package com.example.retrofitroom;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ItemViewModel itemViewModel;
    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;
    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemViewModel = new ViewModelProvider(this).get(ItemViewModel.class);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        itemAdapter = new ItemAdapter();
        recyclerView.setAdapter(itemAdapter);

        Button fetchApiButton = findViewById(R.id.buttonLoadFromApi);
        fetchApiButton.setOnClickListener(view -> makeRequest());

        itemViewModel.getAllItems().observe(this, items -> {
            itemAdapter.setItems(items);
            Log.d("MainActivity", "onChanged: " + items);
        });
    }

    private void makeRequest() {
        RetrofitClient.getApiService().getItems().enqueue(new retrofit2.Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    itemViewModel.insert(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                Log.d("MainActivity", "onFailure: " + t.getMessage());
            }
        });
    }
}
