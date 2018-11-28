package com.mapit.eirene.mapit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class AboutActivity extends AppCompatActivity {


    private FirebaseAuth firebaseAuth;
    private TextView githubLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        githubLink = (TextView)findViewById(R.id.mitGithub);

        githubLink.setText(
                Html.fromHtml(
                        "<a href=\"https://github.com/gloredto/newMapIT\">github.com/gloredto/newMapIT</a> "));
        githubLink.setMovementMethod(LinkMovementMethod.getInstance());

        firebaseAuth = FirebaseAuth.getInstance();
    }

    private void Logout(){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(AboutActivity.this, LoginActivity.class));
    }

    private void About(){
        startActivity(new Intent(AboutActivity.this, AboutActivity.class));
    }

    private void Maps(){
        startActivity(new Intent(AboutActivity.this, MapsActivity.class));
    }

    private void Profile(){
        startActivity(new Intent(AboutActivity.this, ProfileActivity.class));
    }

    private void Home(){
        startActivity(new Intent(AboutActivity.this, MainActivity.class));
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
        }
        return super.onOptionsItemSelected(item);
    }
}

