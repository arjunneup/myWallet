package com.arjunneup.wallet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn_profile, btn_health, btn_salary, btn_education, btn_grocery,btn_rent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_profile = (Button)findViewById(R.id.buttonProfile);
        btn_salary = (Button)findViewById(R.id.buttonSalary);
        btn_education = (Button)findViewById(R.id.buttonEducation);
        btn_grocery = (Button)findViewById(R.id.buttonGrocery);
        btn_rent = (Button)findViewById(R.id.buttonHouse);
        btn_health = (Button)findViewById(R.id.buttonHealth);


        Intent intent = getIntent();
        final String email = intent.getStringExtra("email");
        final String userName = intent.getStringExtra("userName");
        final String fullName = intent.getStringExtra("fullName");


        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),Profile.class);
                intent.putExtra("email",email);
                intent.putExtra("username",userName);
                intent.putExtra("fullName",fullName);
                startActivity(intent);
                startActivity(new Intent(intent));
                Toast.makeText(MainActivity.this, "Profile button clicked", Toast.LENGTH_SHORT).show();
            }
        });
        btn_salary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Salary.class));
                Toast.makeText(MainActivity.this, "Salary Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        btn_education.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Education.class));
                Toast.makeText(MainActivity.this, "Education Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        btn_health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Health.class));
                Toast.makeText(MainActivity.this, "Health Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        btn_grocery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Grocery.class));
                Toast.makeText(MainActivity.this, "Grocery Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        btn_rent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Rent.class));
                Toast.makeText(MainActivity.this, "Rent Clicked", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
