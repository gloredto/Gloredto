package com.mapit.eirene.mapit;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity {

    private TextView profileName, profileBornWhere, profileBornWhen, profileAge, profileStatus;
    private Button profileUpdate, changePassword;
    private ImageView profilePicture;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseStorage firebaseStorage;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setupUIViews();

        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();

        DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid()).child("User");
        DatabaseReference statusReference = firebaseDatabase.getReference(firebaseAuth.getUid()).child("Status");

        StorageReference storageReference = firebaseStorage.getReference();
        storageReference.child(firebaseAuth.getUid()).child("Images/Profile_Pic").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).fit().centerCrop().into(profilePicture);
            }
        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
                profileName.setText("Name : " + userProfile.getUserName());
                profileBornWhere.setText("Place of Birth  : " +userProfile.getUserBornWhere());
                profileBornWhen.setText("Date of Birth : " +userProfile.getUserBornWhen());
                profileAge.setText("Age : " +userProfile.getUserAge());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ProfileActivity.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });

        statusReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserStatus statusStatus = dataSnapshot.child("Quote").getValue(UserStatus.class);
                profileStatus.setText("Status : " +statusStatus.getUserStatus());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ProfileActivity.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });



        profileUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, UpdateProfileActivity.class));
            }
        });

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, UpdatePasswordActivity.class));
            }
        });

    }

    private void Logout(){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
    }

    private void About(){
        startActivity(new Intent(ProfileActivity.this, AboutActivity.class));
    }

    private void Profile(){
        startActivity(new Intent(ProfileActivity.this, ProfileActivity.class));
    }

    private void Maps(){
        startActivity(new Intent(ProfileActivity.this, MapsActivity.class));
    }

    private void Home(){
        startActivity(new Intent(ProfileActivity.this, MainActivity.class));
    }

    private void Status(){
        startActivity(new Intent(ProfileActivity.this, StatusActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    private void setupUIViews(){
        profileName = findViewById(R.id.mitUsernameUpdate);
        profileBornWhen = findViewById(R.id.mitBornWhenUpdate);
        profileBornWhere = findViewById(R.id.mitBornWhereUpdate);
        profileUpdate = findViewById(R.id.btnRegistration);
        profileAge = findViewById(R.id.mitAgeUpdate);
        profileStatus = findViewById(R.id.mitStatus);
        profilePicture = findViewById(R.id.mitProfilePicture);
        changePassword = findViewById(R.id.btnChangePassword);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case  R.id.logoutMenu:{
                Logout();
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
            case R.id.homeMenu:{
                Home();
            }
            break;
            case R.id.statusMenu:{
                Status();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
        }
        return super.onContextItemSelected(item);
    }

}
