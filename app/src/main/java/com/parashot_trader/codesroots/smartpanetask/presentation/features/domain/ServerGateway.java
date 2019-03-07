package com.parashot_trader.codesroots.smartpanetask.presentation.features.domain;

import com.parashot_trader.codesroots.smartpanetask.presentation.features.entities.Countries;
import com.parashot_trader.codesroots.smartpanetask.presentation.features.entities.LoginResponse;
import com.parashot_trader.codesroots.smartpanetask.presentation.features.entities.RegisterResponse;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServerGateway {

    @GET("http://restcountries.eu/rest/v1/all")
    Observable<List<Countries>> getAllCountries();

    @FormUrlEncoded
    @POST("AndriodAPI/Register")
    Call<RegisterResponse> Register(
            @Field("name") String name,
            @Field("username") String username,
            @Field("mobile") String mobile,
            @Field("password") String password,
            @Field("mail") String mail,
            @Field("gender") int gender
    );

    @FormUrlEncoded
    @POST("AndriodAPI/LogIn")
    Call<LoginResponse> Login(
            @Field("username") String username,
            @Field("password") String password
    );
}
