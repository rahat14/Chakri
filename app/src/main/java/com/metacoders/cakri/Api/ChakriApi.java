package com.metacoders.cakri.Api;

import com.metacoders.cakri.Models.JobCircularReponseModel;
import com.metacoders.cakri.Models.JobPrepResponseModel;
import com.metacoders.cakri.Models.ModelQustionListResponse;
import com.metacoders.cakri.Models.StartUpResponse;
import com.metacoders.cakri.Models.qusizModel;

import java.util.List;

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
    // job-circular via category job-circular/fillter/{cat_id}
    @GET("job-circular/fillter/{cat_id}")
    Call<JobCircularReponseModel> GetJobCirCularViaCategory(
            @Path("cat_id") String cat_id,
            @Query("page") int page
    );

    // get whole qustion list

    @GET("model-qus/whole/{cat_id}")
    Call<ModelQustionListResponse> getWholeQustionList(
            @Path("cat_id") String cat_id,
            @Query("page") int page
    );

    @GET("model-qus/subjective/{cat_id}/{sub_cat_id}")
    Call<ModelQustionListResponse> getSubjectiveQustionList(
            @Path("cat_id") String cat_id,
            @Path("sub_cat_id") String sub_cat_id,
            @Query("page") int page
    );


    // single model question response
    @GET("model-qus/qus-list/{id}")
    Call<List<qusizModel>> getQusListByModelQustionId(
            @Path("id") int id
    );



    // best job circular
    @GET("job-circular/best")
    Call<JobCircularReponseModel> GetBestJobCirCular(

            @Query("page") int page
    );
// start up service
    @GET("start-up")
    Call<StartUpResponse> getInitialData();

}
