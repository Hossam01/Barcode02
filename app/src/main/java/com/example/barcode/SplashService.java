package com.example.barcode;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.Nullable;

public class SplashService extends IntentService {
    Context mContext;

    public SplashService() {
        super("test");
    }

    public Intent startService(Context mContext){
        Intent intent = new Intent(mContext,SplashService.class);
        this.mContext = mContext;
        return intent;
    }
    public SplashService(Context mContext) {
        super("test");
        this.mContext = mContext;
    }

    public void startActionBaz(Context context) {
        Intent intent = new Intent(context, SplashService.class);
        this.mContext=context;
        context.startService(intent);
        Log.d("startBaz",Thread.currentThread().getName());
    }
    @Override
    public void onCreate() {
        super.onCreate();

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent mainIntent = new Intent(getApplicationContext(), Main2Activity.class);
                mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(mainIntent);
            }
        }, 2500);

    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        // Toast.makeText(this,"start",Toast.LENGTH_SHORT).show();

        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        //Toast.makeText(this,"destroy", Toast.LENGTH_SHORT).show();

        super.onDestroy();
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }
}
