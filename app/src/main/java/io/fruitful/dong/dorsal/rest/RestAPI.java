package io.fruitful.dong.dorsal.rest;

import io.fruitful.dong.dorsal.model.loginRespone.BodyLogin;
import io.fruitful.dong.dorsal.model.loginRespone.ResponeLogin;
import io.fruitful.dong.dorsal.model.activateRespone.BodyActivate;
import io.fruitful.dong.dorsal.model.activateRespone.ResuPassCode;
import io.fruitful.dong.dorsal.model.listResportRespone.BodyListReport;
import io.fruitful.dong.dorsal.model.listResportRespone.ResultList;
import io.fruitful.dong.dorsal.model.registerRespone.ResultRegister;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by Ares on 8/30/2016.
 */

public interface RestAPI {

    @POST("/login")
    Call<ResponeLogin> postUser(@Header("X-Auth-Username") String userName, @Header("X-Auth-Password") String password, @Body BodyLogin body);

    @POST("/report/list")
    Call<ResultList> RESULT_LIST_CALL(@Header("X-Auth-Token") String Token, @Body BodyListReport bodyListReport);

    @POST("/public/account/activate")
    Call<ResuPassCode> RESULT_ACTIVATE_CALL(@Header("Content-Type") String type , @Body BodyActivate bodyActivate);

    @Multipart
    @POST("/register")
    Call<ResultRegister> register(@Part("data") RequestBody jsonBody, @Part MultipartBody.Part file);
}
