package com.arjunneup.wallet;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Profile extends AppCompatActivity {

    TextView txtEmail, txtusername,fname,txtgender;
    DatabaseReference reference;
    private ImageView mimageView;
    private static final int REQUEST_IMAGE_CAPTURE = 101;
    Bundle bundle;
    ListView Profile;
    private Object Context;

    List<wallet> users;
    private FirebaseAuth firebaseAuth;


    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        db = FirebaseDatabase.getInstance().getReference("wallet");
        users = new ArrayList<>();
        txtusername = (TextView) findViewById(R.id.uName);
        txtEmail = (TextView) findViewById(R.id.eAddress);
       txtgender = (TextView)findViewById(R.id.gender);
        fname = (TextView) findViewById(R.id.fName);

        mimageView = findViewById(R.id.imageView);

        firebaseAuth = FirebaseAuth.getInstance();
        Intent intent = getIntent();


        txtEmail.setText(firebaseAuth.getCurrentUser().getEmail().toString());


        /** Check if this device has a camera */


    }

    @Override
    protected void onStart() {
        super.onStart();

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapshot: dataSnapshot.getChildren() ){

                    wallet artist =  postSnapshot.getValue(wallet.class);
                    users.add(artist);
                }

                for (int i=0; i< users.size(); i++){
                    if(users.get(i).getEmail().equals(firebaseAuth.getCurrentUser().getEmail().toString()))
                    {
                        txtusername.setText(users.get(i).getUsername());
                        fname.setText(users.get(i).getFullName());
                        txtgender.setText(users.get(i).getGender());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private boolean checkCameraHardware(android.content.Context context){
        if(context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            return true;
        }
        else{
            return false;
        }
    }
    public void takePicture(View view) {
        Intent imageTakeIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (imageTakeIntent.resolveActivity(getPackageManager())!=null);
        {
            startActivityForResult(imageTakeIntent,REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mimageView.setImageBitmap(imageBitmap);
        }

    }
}
