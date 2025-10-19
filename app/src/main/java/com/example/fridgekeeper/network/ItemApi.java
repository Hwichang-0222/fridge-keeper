package com.example.fridgekeeper.network;

import com.example.fridgekeeper.model.Item;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.DELETE;
import retrofit2.http.Path;
import retrofit2.http.Body;

public interface ItemApi {

    @GET("api/items")
    Call<List<Item>> getItems();

    @GET("api/items/{id}")
    Call<Item> getItem(@Path("id") long id);

    @POST("api/items")
    Call<Void> addItem(@Body Item item);

    @PUT("api/items/{id}")
    Call<Void> updateItem(@Path("id") long id, @Body Item item);

    @DELETE("api/items/{id}")
    Call<Void> deleteItem(@Path("id") long id);
}
