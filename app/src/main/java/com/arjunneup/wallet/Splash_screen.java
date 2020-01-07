package com.arjunneup.wallet;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash_screen extends AppCompatActivity {

    private boolean backbuttonPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent loginIntent = new Intent(Splash_screen.this, Login_form.class);
                startActivity(loginIntent);
                finish();
            }
        },
                3000);

    }

    @Override
    public void onBackPressed(){
        backbuttonPressed =true;
        super.onBackPressed();
    }
}
