package com.example.barcode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.loader.app.LoaderManager;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.barcode.databinding.ActivityMainBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static android.util.Patterns.EMAIL_ADDRESS;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback , DatePickerDialog.OnDateSetListener{

    ActivityViewModel spalshViewModle;
    SupportMapFragment mapFragment;
    ActivityMainBinding activityLoginBinding;
    Double x,y;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityLoginBinding= DataBindingUtil.setContentView(this, R.layout.activity_main);
        spalshViewModle= ViewModelProviders.of(this).get(ActivityViewModel.class);

        activityLoginBinding.setViewModel(spalshViewModle);
        activityLoginBinding.setLifecycleOwner(this);
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.frag);


        mapFragment.getMapAsync(this);

        activityLoginBinding.location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapFragment.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(GoogleMap googleMap) {
                        googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                        getCurrentLocation(googleMap);
                        x=googleMap.getMyLocation().getAltitude();
                        y=googleMap.getMyLocation().getLatitude();
                    }
                });
            }
        });
        activityLoginBinding.brithdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

        activityLoginBinding.done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!EMAIL_ADDRESS.matcher(activityLoginBinding.email.getText().toString()).matches()) {
                    Toast.makeText(MainActivity.this, "Please enter valid email", Toast.LENGTH_SHORT).show();
                }
                else if(activityLoginBinding.number.getText().toString().length()<11) {
                    Toast.makeText(MainActivity.this, "Number must contain 11 characters", Toast.LENGTH_SHORT).show();
                }
                    spalshViewModle.actionPicker(  activityLoginBinding.fullname.getText().toString(),
                        activityLoginBinding.email.getText().toString(),
                        activityLoginBinding.number.getText().toString(),
                        activityLoginBinding.brithdate.getText().toString(),
                        x,y,MainActivity.this);


            }
        });
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        getCurrentLocation(googleMap);
    }

    void getCurrentLocation(GoogleMap map)
    {
        map.setMyLocationEnabled(true);
        Location myLocation  = map.getMyLocation();
        if(myLocation!=null)
        {
            double dLatitude = myLocation.getLatitude();
            double dLongitude = myLocation.getLongitude();
            map.addMarker(new MarkerOptions().position(new LatLng(dLatitude, dLongitude))
                    .title("My Location").icon(BitmapDescriptorFactory
                            .defaultMarker(BitmapDescriptorFactory.HUE_RED)));
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(dLatitude, dLongitude), 8));
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Unable to fetch the current location", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        activityLoginBinding.brithdate.setText(currentDateString);
    }






}
