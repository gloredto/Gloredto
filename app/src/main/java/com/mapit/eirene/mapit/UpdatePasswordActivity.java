package com.mapit.eirene.mapit;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UpdatePasswordActivity extends AppCompatActivity {
    private EditText newPassword;
    private Button saveUpdatePassword;
    private FirebaseUser firebaseUser;
    //private FirebaseAuth firebaseAuth;


    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);

        newPassword = findViewById(R.id.mitPasswordUpdate);
        saveUpdatePassword = findViewById(R.id.btnPasswordUpdate);

        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        //firebaseAuth = FirebaseAuth.getInstance();

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        saveUpdatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userPasswordNew = newPassword.getText().toString();
                firebaseUser.updatePassword(userPasswordNew).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(UpdatePasswordActivity.this, "Password Changed", Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(UpdatePasswordActivity.this, "Password Changed is Failed", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });

                startActivity(new Intent(UpdatePasswordActivity.this, LoginActivity.class));
            }
        });



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
