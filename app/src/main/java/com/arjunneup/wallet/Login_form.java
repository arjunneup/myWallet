package com.arjunneup.wallet;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_form extends AppCompatActivity {

    private EditText etEmail, etPassword;
    Button btn_login;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);

        etEmail = (EditText)findViewById(R.id.txtEmail);
        etPassword = (EditText)findViewById(R.id.txtPassword);
        btn_login = (Button) findViewById(R.id.btnLogin);

        firebaseAuth = FirebaseAuth.getInstance();

     btn_login.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             String email = etEmail.getText().toString().trim();
             String password = etPassword.getText().toString().trim();

            if(TextUtils.isEmpty(email)){
                Toast.makeText(Login_form.this, "Enter email", Toast.LENGTH_SHORT).show();
                return;
            }
             if(TextUtils.isEmpty(password)){
                 Toast.makeText(Login_form.this, "Enter password", Toast.LENGTH_SHORT).show();
                 return;
             }
             if(password.length()<6){
                 Toast.makeText(Login_form.this, "Password too short", Toast.LENGTH_SHORT).show();
             }

             firebaseAuth.signInWithEmailAndPassword(email, password)
                     .addOnCompleteListener(Login_form.this, new OnCompleteListener<AuthResult>() {
                         @Override
                         public void onComplete(@NonNull Task<AuthResult> task) {
                             if (task.isSuccessful()) {
                                 String firstName = firebaseAuth.getCurrentUser().getEmail();
                                 String userName = firebaseAuth.getCurrentUser().getDisplayName();
                                 Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                 intent.putExtra("email",firstName);
                                 intent.putExtra("username",userName);
                                 startActivity(intent);
                             } else {
                                 // If sign in fails, display a message to the user.
                                 Toast.makeText(Login_form.this, "Login failed or User not available", Toast.LENGTH_SHORT).show();

                             }
                         }
                     });

         }
     });

    }


    public void btn_signupForm(View view) {
        startActivity(new Intent(getApplicationContext(),Signup_Form.class));
    }
}
