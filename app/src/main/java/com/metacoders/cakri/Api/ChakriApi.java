package com.metacoders.cakri.Api;

import com.metacoders.cakri.Models.BookmarkModel;
import com.metacoders.cakri.Models.CommentResponse;
import com.metacoders.cakri.Models.FaqModel;
import com.metacoders.cakri.Models.JobCircularReponseModel;
import com.metacoders.cakri.Models.JobPrepModel;
import com.metacoders.cakri.Models.LeaderBoardModel;
import com.metacoders.cakri.Models.Response_login;
import com.metacoders.cakri.Models.ModelQustionListResponse;
import com.metacoders.cakri.Models.MsgModel;
import com.metacoders.cakri.Models.NotificaitonResponse;
import com.metacoders.cakri.Models.SearchResponse;
import com.metacoders.cakri.Models.StartUpResponse;
import com.metacoders.cakri.Models.qusizModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
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


    @GET("get-bookmark/{uid}")
    Call<List<BookmarkModel>> getBookMark(
            @Path("uid")int uid
    );
//$u_id , $p_id , $type ,$title
    @FormUrlEncoded
    @POST("save-bookmark")
    Call<MsgModel> insertBookMark(
            @Field("user_id") int uid,
            @Field("post_id")int p_id,
            @Field("post_type")int type,
            @Field("title")String title
    );


    // nottificaiton  service
    @GET("notifications")
    Call<NotificaitonResponse> getNotificaiton(
            @Query("page") int page
    );
    @GET("single/{type}/{id}")
    Call<JobPrepModel> getSingle(
            @Path("type") String type ,
            @Path("id") String id
    );

    //delete the bookmark
    @GET("delete-bookmark/{u_id}/{p_id}")
    Call<MsgModel>deleteBookmark(
            @Path("u_id") int u_id ,
            @Path("p_id") String p_id
    ) ;

    //all all/666
    @GET("all/{type}")
    Call<JobCircularReponseModel> getAll(
            @Path("type") String type,@Query("page") int page  );


    @GET("check/phone/{ph}")
    Call<MsgModel> checkPhone(@Path("ph") String ph);


    @GET("model_qus_rank/{id}")
    Call<List<LeaderBoardModel>> getRank(@Path("id") String is);

    //search list
    @GET("search")
    Call<SearchResponse> getSearch();
    @GET("faq/{id}")
    Call<List<FaqModel>> getFaqList(@Path("id") int id);
    //comment list
    @GET("comments/{id}")
    Call<CommentResponse> getCommentList(@Path("id") String id);
    @GET("login/{ph}/{pass}")
    Call<Response_login> login(@Path("ph") String ph   , @Path("pass") String pass  );

    // create

    // create the comment
    // server
//    $single_comment ->post_id = $request->post_id ;
//    $single_comment->post_type = $request->post_type;
//    $single_comment->content = $request->content;
//    $single_comment->user_name = $request->user_name;
//    $single_comment->user_id = $request->user_id;
//    $single_comment->date = $request->date;
    @FormUrlEncoded
    @POST("create/comment")
    Call<MsgModel> PostAComment(
            @Field("post_id") int post_id,
            @Field("post_type") int post_type,
            @Field("content") String content,
            @Field("user_name") String user_name,
            @Field("user_id") int user_id,
            @Field("date") String date
    );

    @FormUrlEncoded
    @POST("create/user")
    Call<MsgModel> CreateAUser(
            @Field("full_name") String full_name,
            @Field("password") String password,
            @Field("adress") String adress,
            @Field("user_name") String user_name,
            @Field("phone") String phone,
            @Field("email") String email

    );


    @FormUrlEncoded
    @POST("create/marksheet")
    Call<MsgModel> CreateMarksheet(
            @Field("user_id") int user_id,
            @Field("qus_id") int qus_id,
            @Field("score") double score,
            @Field("mark_sheet") String mark_sheet ,
            @Field("user_name") String user_name


    );

    @FormUrlEncoded
    @POST("create/faq")
    Call<MsgModel> CreateFAQ(
            @Field("user_id") int user_id,
            @Field("qus") String qus,
            @Field("ans") String ans


    );


}
