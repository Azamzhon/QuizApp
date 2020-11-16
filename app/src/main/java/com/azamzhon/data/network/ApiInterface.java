package com.azamzhon.data.network;

import com.azamzhon.data.models.CategoriesResponse;
import com.azamzhon.data.models.QuestionResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("api.php")
    Call<QuestionResponse> getQuestions(@Query("amount") int amount,
                                        @Query("category") int category,
                                        @Query("difficulty") String difficulty);

    @GET("api_category.php")
    Call<CategoriesResponse> getCategories();

}
