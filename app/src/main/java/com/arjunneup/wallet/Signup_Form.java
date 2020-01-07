package com.arjunneup.wallet;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Signup_Form extends AppCompatActivity {

    EditText txtEmail, txtPassword, txtConfirmPassword,txtName,txtusername;
    Button btn_register;
    RadioButton radioGenderMale, radioGenderFemale, radioGenderOthers;
    String gender ="";


    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;


    ProgressBar progressBar;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup__form);

        txtName = (EditText)findViewById(R.id.txtFullname);
        txtusername = (EditText)findViewById(R.id.txtUsername);
        txtEmail = (EditText)findViewById(R.id.txt_email);
        txtPassword = (EditText)findViewById(R.id.txt_password);
        txtConfirmPassword = (EditText)findViewById(R.id.txt_confirmpassword);
        radioGenderMale = (RadioButton) findViewById(R.id.radioMale);
        radioGenderFemale = (RadioButton) findViewById(R.id.radioFemale);


        databaseReference = FirebaseDatabase.getInstance().getReference("wallet");
        firebaseAuth = FirebaseAuth.getInstance();


        btn_register = (Button)findViewById(R.id.btnRegister);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String fullName = txtName.getText().toString().trim();
                final String username = txtusername.getText().toString().trim();
                final String email = txtEmail.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();
                String confirmPassword = txtConfirmPassword.getText().toString().trim();

                if (radioGenderMale.isChecked()){
                    gender = "Male";
                }
                if(radioGenderFemale.isChecked()){
                    gender = "Female";
                }


                if (TextUtils.isEmpty(fullName)){
                    Toast.makeText(Signup_Form.this, "Please enter full name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(username)){
                    Toast.makeText(Signup_Form.this, "Please enter username", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(email)){
                    Toast.makeText(Signup_Form.this, "Please enter email address", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(email)){
                    Toast.makeText(Signup_Form.this, "Please enter email address", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(Signup_Form.this, "Please enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(confirmPassword)){
                    Toast.makeText(Signup_Form.this, "Please enter confirm password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length()<6){
                    Toast.makeText(Signup_Form.this, "Password too short", Toast.LENGTH_SHORT).show();
                }

                progressBar.setVisibility(View.VISIBLE);

                if (password.equals(confirmPassword)){

                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(Signup_Form.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    progressBar.setVisibility(View.GONE);

                                    if (task.isSuccessful()) {

                                        wallet information = new wallet(
                                          fullName,
                                          username,
                                          email,
                                          gender
                                        );
                                        FirebaseDatabase.getInstance().getReference("wallet")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(information).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(Signup_Form.this, "Registration Complete", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(getApplicationContext(),Login_form.class));
                                            }
                                        });
                                    } else {
                                        Toast.makeText(Signup_Form.this, "Authentication failed", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                }


            }
        });
    }
}
