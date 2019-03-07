package com.parashot_trader.codesroots.smartpanetask.domain;

import com.parashot_trader.codesroots.smartpanetask.entities.Countries;
import com.parashot_trader.codesroots.smartpanetask.entities.LoginResponse;
import com.parashot_trader.codesroots.smartpanetask.entities.RegisterResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

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
