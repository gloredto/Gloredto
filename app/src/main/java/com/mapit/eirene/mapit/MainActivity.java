package com.mapit.eirene.mapit;

import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private StorageReference mStorage;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseStorage firebaseStorage;
    private ListView mUserList;

    private ArrayList<Image> mUserPic;

    //Array List
    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    private UserProfile userProfile;

    private ImageView profilePictureview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUserList = (ListView) findViewById(R.id.user_list);
        profilePictureview = (ImageView) findViewById(R.id.user_profileview);


        userProfile = new UserProfile();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();


        mDatabase = firebaseDatabase.getReference();
        mStorage = firebaseStorage.getReference();
//        mStorage.child(firebaseAuth.getUid()).child("Images/Profile_Pic").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//            @Override
//            public void onSuccess(Uri uri) {
//                Picasso.get().load(uri).fit().centerCrop().into(profilePicture);
//            }
//        });

        arrayList = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, R.layout.user_info, R.id.userInfo, arrayList);



        mUserList.setAdapter(adapter);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren())
                {

/*                    mStorage.child(mDatabase.getKey()).child("Images/Profile_Pic").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                            Picasso.get().load(uri).fit().centerCrop().into(profilePictureview);
                        }
                    });*/

                    userProfile = ds.child("User").getValue(UserProfile.class);
                    final String userName = userProfile.getUserName();
                    final String userBornWhere = userProfile.getUserBornWhere();
                    final String userBornWhen = userProfile.getUserBornWhen();
                    final String userAge = userProfile.getUserAge();



                    arrayList.add(userName + "\n" +userBornWhere + "\n" +userBornWhen + "\n" +userAge);

                }
                mUserList.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        /*mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                String userName = dataSnapshot.child("User").child("userName").getValue(String.class);
                //String userEmail = dataSnapshot.child("User").child("userEmail").getValue(String.class);

                arrayList.add(userName);
                //arrayList.add(userEmail);

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/


    }




















    private void Logout(){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }

    private void About(){
        startActivity(new Intent(MainActivity.this, AboutActivity.class));
    }

    private void Profile(){
        startActivity(new Intent(MainActivity.this, ProfileActivity.class));
    }

    private void Maps(){
        startActivity(new Intent(MainActivity.this, MapsActivity.class));
    }

    private void Home(){
        startActivity(new Intent(MainActivity.this, MainActivity.class));
    }

    private void Status(){
        startActivity(new Intent(MainActivity.this, StatusActivity.class));
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
                Status();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
