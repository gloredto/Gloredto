package com.mapit.eirene.mapit;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class StatusActivity extends AppCompatActivity {

    static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    LocationManager locationManager;

    private EditText userStatus;
    private Button userstatusShare;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        setupUIViews();

        userstatusShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
                getLocation();
                finish();
            }
        });

    }

    private void getLocation(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        }else{
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            if(location != null) {
                firebaseAuth = FirebaseAuth.getInstance();
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                final DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());
                String status = userStatus.getText().toString();
                UserStatus userStatusQuote = new UserStatus(status);
                myRef.child("Status").child("Quote").setValue(userStatusQuote);
                double userLatitude = location.getLatitude();
                double userLongitude = location.getLongitude();
                UserStatus userStatus  = new UserStatus(userLatitude,userLongitude, status);
                myRef.child("Status").child("Position").setValue(userStatus);

            }else{
                firebaseAuth = FirebaseAuth.getInstance();
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                final DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());
                String status = userStatus.getText().toString();
                UserStatus userStatusQuote = new UserStatus(status);
                myRef.child("Status").child("Quote").setValue(userStatusQuote);

            }
        }
    }

    private void setupUIViews(){
        userStatus = findViewById(R.id.mitStatus);
        userstatusShare = findViewById(R.id.btnShare);
    }

    private void Logout(){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(StatusActivity.this, LoginActivity.class));
    }

    private void About(){
        startActivity(new Intent(StatusActivity.this, AboutActivity.class));
    }

    private void Profile(){
        startActivity(new Intent(StatusActivity.this, ProfileActivity.class));
    }

    private void Maps(){
        startActivity(new Intent(StatusActivity.this, MapsActivity.class));
    }

    private void Home(){
        startActivity(new Intent(StatusActivity.this, MainActivity.class));
    }

    private void Statusmenu(){
        startActivity(new Intent(StatusActivity.this, StatusActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case  R.id.logoutMenu:{
                Logout();
            }
            break;
            case  R.id.homeMenu:{
                Home();
            }
            break;
            case R.id.aboutMenu:{
                About();
            }
            break;
            case R.id.profileMenu:{
                Profile();
            }
            break;
            case R.id.mapsMenu:{
                Maps();
            }
            break;
            case R.id.statusMenu:{
                Statusmenu();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
