package com.example.barcode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.example.barcode.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {

    SplashViewModel spalshViewModle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySplashBinding activityLoginBinding= DataBindingUtil.setContentView(this, R.layout.activity_splash);
        spalshViewModle= ViewModelProviders.of(this).get(SplashViewModel.class);

        activityLoginBinding.setViewModel(spalshViewModle);
        activityLoginBinding.setLifecycleOwner(this);
        spalshViewModle.splashGo(SplashActivity.this);

    }
    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
