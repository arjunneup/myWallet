package com.arjunneup.wallet;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Grocery extends AppCompatActivity {
    ListView listView;
    ListView items;
    EditText txtData;
    Button btnAdd, btnUpdate;
    Spinner monthSpinner;
    String [] month;

    ArrayList<GroceryModel> rent;
    ArrayAdapter myAdapter;

    Integer indexVal;
    String item;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery);

        listView = (ListView)findViewById(R.id.list);
        btnAdd = (Button)findViewById(R.id.buttonAdd);
        btnUpdate = (Button)findViewById(R.id.buttonUpdate);
        txtData = (EditText)findViewById(R.id.textData);
        monthSpinner = (Spinner)findViewById(R.id.monthSpinner);
        rent = new ArrayList<>();

        month = getResources().getStringArray(R.array.monthNames);

        myAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,month);
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(myAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringval = txtData.getText().toString().trim();
                if(TextUtils.isEmpty(stringval)){
                    Toast.makeText(Grocery.this, "Enter Grocery", Toast.LENGTH_SHORT).show();
                }
                else{
                    rent.add(new GroceryModel(Integer.parseInt(txtData.getText().toString()), monthSpinner.getSelectedItem().toString()));
                    myAdapter.notifyDataSetChanged();
                    txtData.setText("");


                    // Construct the data source
                    // Create the adapter to convert the array to views
                    CustomeGroceryAdapter adapter = new CustomeGroceryAdapter(Grocery.this, rent);
                    // Attach the adapter to a ListView
                    ListView listView = (ListView) findViewById(R.id.list);
                    listView.setAdapter(adapter);
                }
            }
        });

        monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long id) {
                item = adapterView.getItemAtPosition(i).toString() + " is selected";

                Toast.makeText(Grocery.this, item, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

}
