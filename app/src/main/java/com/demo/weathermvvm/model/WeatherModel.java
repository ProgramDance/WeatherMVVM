package com.demo.weathermvvm.model;

import com.demo.weathermvvm.http.HttpHelper;
import com.demo.weathermvvm.model.bean.WeatherInfo;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class WeatherModel {
    public void getWeatherInfo(String city, final GetRemoteListener<WeatherInfo> listener) {
        HttpHelper.getWeatherInfoApi(city).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WeatherInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(WeatherInfo value) {
                        listener.onSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
