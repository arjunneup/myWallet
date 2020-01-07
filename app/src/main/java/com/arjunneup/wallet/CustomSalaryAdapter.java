package com.arjunneup.wallet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomSalaryAdapter extends ArrayAdapter<SalaryModel> {
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
            SalaryModel salaryModel = getItem(position);

            // Check if an existing view is being reused, otherwise inflate the view

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view, parent, false);
            }
            // Lookup view for data population

            TextView listSalary = (TextView) convertView.findViewById(R.id.listSalary);
            TextView listMonth = (TextView) convertView.findViewById(R.id.listMonth);

            // Populate the data into the template view using the data object
            listSalary.setText(salaryModel.salary + "");
            listMonth.setText(salaryModel.month);

//            // Return the completed view to render on screen
            return convertView;
    }

    public CustomSalaryAdapter(Context context, ArrayList<SalaryModel> salary) {
        super(context, 0, salary);
    }
}

