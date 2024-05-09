package com.example.day09.data.api;

import com.example.day09.data.model.login.Login;
import com.example.day09.data.model.register.Register;
import retrofit2.Call;
import retrofit2.http.*;

public interface ApiService {
    @FormUrlEncoded
    @POST("login.php")
    Call<Login> LoginResponse(
            @Field("username") String username,
            @Field("password") String password
    );
    @FormUrlEncoded
    @POST("register.php")
    Call<Register> RegisterResponse(
            @Field("username") String username,
            @Field("name") String name,
            @Field("password") String password
    );
}