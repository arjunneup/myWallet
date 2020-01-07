package com.arjunneup.wallet;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Salary extends AppCompatActivity {

    ListView listView;
    ListView items;
    EditText txtData;
    Button btnAdd, btnUpdate;
    Spinner monthSpinner;
    String [] month;
    Button btn_save;
    ArrayList<SalaryModel> salary;
    ArrayAdapter myAdapter;

    Integer indexVal;
    String item;

    //firebase database
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salary);

        listView = (ListView)findViewById(R.id.list);
        btnAdd = (Button)findViewById(R.id.buttonAdd);
        btnUpdate = (Button)findViewById(R.id.buttonUpdate);
        txtData = (EditText)findViewById(R.id.textData);
        monthSpinner = (Spinner)findViewById(R.id.monthSpinner);
        salary = new ArrayList<>();

        month = getResources().getStringArray(R.array.monthNames);

        myAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,month);
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(myAdapter);

        //firebase database
        databaseReference = FirebaseDatabase.getInstance().getReference("salary");

        //firebase database
        btn_save = (Button)findViewById(R.id.buttonSave);



        //setup list view


//        myAdapter = new ArrayAdapter<String>(
//                this,android.R.layout.simple_list_item_1,salary);
//        listView.setAdapter(myAdapter);

        //add items
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringval = txtData.getText().toString().trim();
                if(TextUtils.isEmpty(stringval)){
                    Toast.makeText(Salary.this, "Enter salary", Toast.LENGTH_SHORT).show();
                }
                else{
                    salary.add(new SalaryModel(Integer.parseInt(txtData.getText().toString()), monthSpinner.getSelectedItem().toString()));
                    myAdapter.notifyDataSetChanged();
                    txtData.setText("");


                    // Construct the data source
                    // Create the adapter to convert the array to views
                    CustomSalaryAdapter adapter = new CustomSalaryAdapter(Salary.this, salary);
                    // Attach the adapter to a ListView
                    ListView listView = (ListView) findViewById(R.id.list);
                    listView.setAdapter(adapter);
                }
            }
        });




        //select item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                item = adapterView.getItemAtPosition(i).toString() + " is selected, Please press long to delete";
                indexVal =i;
                Toast.makeText(Salary.this, item, Toast.LENGTH_SHORT).show();
            }
        });

        //spinner
        monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long id) {
                item = adapterView.getItemAtPosition(i).toString() + " is selected";

                Toast.makeText(Salary.this, item, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //update items
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringval = txtData.getText().toString().trim();
                if(TextUtils.isEmpty(stringval)){
                    Toast.makeText(Salary.this, "Enter number to update", Toast.LENGTH_SHORT).show();
                }
                else{
                    //salary.set(indexVal,stringval);

                    myAdapter.notifyDataSetChanged();
                }
            }
        });

        /* delete items */
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                item = adapterView.getItemAtPosition(i).toString() + " is deleted";
                Toast.makeText(Salary.this, item, Toast.LENGTH_SHORT).show();
                salary.remove(i);
                myAdapter.notifyDataSetChanged();
                return true;
            }
        });


    }
}
