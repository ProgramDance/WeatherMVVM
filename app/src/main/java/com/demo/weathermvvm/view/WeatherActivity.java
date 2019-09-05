package com.demo.weathermvvm.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.demo.weathermvvm.R;
import com.demo.weathermvvm.databinding.ActivityWeatherBinding;
import com.demo.weathermvvm.viewmodel.WeatherViewModel;

public class WeatherActivity extends AppCompatActivity implements View.OnClickListener {
    private WeatherViewModel viewModel;
    private ActivityWeatherBinding dataBinding;
    private MaterialDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewModel();
        initDataBinding();
        setListener();
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())
        ).get(WeatherViewModel.class);
        viewModel.getIsLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    showLoadingDialog();
                } else {
                    dismissLoadingDialog();
                }
            }
        });
    }

    private void initDataBinding() {
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_weather);
        dataBinding.setViewModel(viewModel);
        dataBinding.setLifecycleOwner(this);
    }

    private void setListener() {
        findViewById(R.id.btn_request).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_request:
                viewModel.getWeatherWithNet();
                break;
        }
    }

    /**
     * 代码显示Dialog
     */
    private void showLoadingDialog() {
        if (loadingDialog == null) {
            loadingDialog = new MaterialDialog.Builder(this)
                    .content("拼命加载中...")
                    .progress(true, 0)
                    .build();
        }

        loadingDialog.show();
    }

    /**
     * 代码隐藏Dialog
     */
    private void dismissLoadingDialog() {
        loadingDialog.dismiss();
    }
}
