package com.demo.weathermvvm.viewmodel;

import com.demo.weathermvvm.model.GetRemoteListener;
import com.demo.weathermvvm.model.WeatherModel;
import com.demo.weathermvvm.model.bean.WeatherInfo;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WeatherViewModel extends ViewModel {
    /**
     * 后台返回查询天气实体对象
     */
    private MutableLiveData<WeatherInfo> weatherInfo = new MutableLiveData<>();
    /**
     * 用户数据查询天气的城市
     */
    private MutableLiveData<String> inputCity = new MutableLiveData<>();
    /**
     * 界面提示的错误信息
     */
    private MutableLiveData<String> errMsg = new MutableLiveData<>();
    /**
     * 是否正在联机标志
     */
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    private WeatherModel weatherModel;

    public WeatherViewModel(){
        weatherModel = new WeatherModel();
    }

    public MutableLiveData<WeatherInfo> getWeatherInfo() {
        return weatherInfo;
    }

    public MutableLiveData<String> getInputCity() {
        return inputCity;
    }

    public MutableLiveData<String> getErrMsg() {
        return errMsg;
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public void getWeatherWithNet(){
        beforeOnlineHandle();

        weatherModel.getWeatherInfo(inputCity.getValue(), new GetRemoteListener<WeatherInfo>() {
            @Override
            public void onSuccess(WeatherInfo weatherInfo) {
                if(weatherInfo.getError_code() == 0) {
                    WeatherViewModel.this.weatherInfo.setValue(weatherInfo);
                }else{
                    errMsg.setValue(weatherInfo.getReason());
                }
                isLoading.setValue(false);
            }

            @Override
            public void onError(String msg) {
                errMsg.setValue(msg);
                isLoading.setValue(false);
            }
        });
    }

    public void beforeOnlineHandle(){
        isLoading.setValue(true);
        errMsg.setValue(null);
    }
}
