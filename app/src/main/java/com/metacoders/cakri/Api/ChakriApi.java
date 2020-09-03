package com.metacoders.cakri.Api;

import com.metacoders.cakri.Models.JobCircularReponseModel;
import com.metacoders.cakri.Models.JobPrepResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ChakriApi {

    @GET("job-circular/best")
    Call<JobCircularReponseModel> response(@Query("page") String page);


    @GET("job-perp/fillter/{cat_id}/{sub_cat_id}")
    Call<JobCircularReponseModel> getPrepList(
            @Path("cat_id") String cat_id,
            @Path("sub_cat_id") String sub_cat_id,
            @Query("page") String page
    );
}
