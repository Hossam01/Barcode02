package com.example.barcode;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.airbnb.lottie.L;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class ActivityViewModel extends ViewModel  {


    MutableLiveData<List<User>> mutableLiveData=new MutableLiveData<>();

    public void actionPicker(String name, String email, String number, String date, Double x, Double y,Context context)
    {
        final ProgressDialog progressdialog = new ProgressDialog(context);
        progressdialog.setMessage("Please Wait....");
        progressdialog.show();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getInstance().getReference().child("result");
        myRef.keepSynced(true);
        User item = new User(name, email, number, date,x,y);
        myRef.push().setValue(item);
        progressdialog.dismiss();
        showdata(item,context);

    }

    public void showdata(User user,Context context)
    {

        Geocoder gcd = new Geocoder(context, Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = gcd.getFromLocation(user.getX(), user.getY(), 1);

        if (addresses.size() > 0) {
            System.out.println(addresses.get(0).getLocality());
        }
        else {
            // do your stuff
        }
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setMessage("name : "+user.getName()+"\n"+"email :"+user.getEmail()+"\n"+"number :"+user.getNumber()+"\n"+"BirthDate :"+user.getDate()+"\n"+"Location :"+addresses.get(0).getLocality());
        builder1.setCancelable(true);
        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert11 = builder1.create();
        alert11.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
