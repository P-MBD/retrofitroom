package com.example.retrofitroom;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("items/")
    Call<List<Item>> getItems();
}
