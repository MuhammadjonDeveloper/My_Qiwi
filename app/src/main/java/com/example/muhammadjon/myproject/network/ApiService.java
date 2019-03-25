package com.example.muhammadjon.myproject.network;

import com.example.muhammadjon.myproject.model.Event;
import com.example.muhammadjon.myproject.model.req.SignInRequest;
import com.example.muhammadjon.myproject.model.res.SignInResponse;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @POST("sign_in")
    Single<SignInResponse> signIn(@Body SignInRequest request);

    @GET("/event/")
    Single<Event> event();
}
