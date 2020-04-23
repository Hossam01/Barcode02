package com.example.barcode;

import android.content.Context;
import android.content.Intent;

import androidx.lifecycle.ViewModel;

public class SplashViewModel extends ViewModel {

    public void splashGo(Context context)
    {
        SplashService serviceTest = new SplashService(context);
        context.startService(new Intent(context,serviceTest.getClass()));
    }
}
