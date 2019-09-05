package com.demo.weathermvvm.http;

import com.demo.weathermvvm.model.bean.WeatherInfo;
import com.demo.weathermvvm.utils.Constants;

import io.reactivex.Observable;

public class HttpHelper {
    public static Observable<WeatherInfo> getWeatherInfoApi(String city){
        return RetrofitClient.getInstance().create(ApiService.class).getWeather(city, Constants.WEATHER_KEY);
    }
}
