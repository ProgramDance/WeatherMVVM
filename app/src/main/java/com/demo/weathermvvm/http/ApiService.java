package com.demo.weathermvvm.http;

import com.demo.weathermvvm.model.bean.WeatherInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("query")
    Observable<WeatherInfo> getWeather(@Query("city") String city, @Query("key") String key);
}
