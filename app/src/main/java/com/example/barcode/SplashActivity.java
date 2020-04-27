package com.example.barcode;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
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
        notification();


    }
    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void notification()
    {
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        String CHANNEL_ID="MYCHANNEL";
        NotificationChannel notificationChannel=new NotificationChannel(CHANNEL_ID,"name",NotificationManager.IMPORTANCE_LOW);
        PendingIntent pendingIntent=PendingIntent.getActivity(getApplicationContext(),1,intent,0);
        Notification notification=new Notification.Builder(getApplicationContext(),CHANNEL_ID)
                .setContentText("Add Data")
                .setContentTitle("Barcode")
                .setContentIntent(pendingIntent)
                .addAction(android.R.drawable.sym_action_chat,"Title",pendingIntent)
                .setChannelId(CHANNEL_ID)
                .setSmallIcon(android.R.drawable.sym_action_chat)
                .build();

        NotificationManager notificationManager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(notificationChannel);
        notificationManager.notify(1,notification);
    }
}
