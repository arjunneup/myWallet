package com.arjunneup.wallet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomRentAdapter extends ArrayAdapter<RentModel> {

    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {

        // Get the data item for this position
        RentModel rentModel = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view, parent, false);
        }
        // Lookup view for data population

        TextView listRent = (TextView) convertView.findViewById(R.id.listSalary);
        TextView listMonth = (TextView) convertView.findViewById(R.id.listMonth);

        // Populate the data into the template view using the data object
        listRent.setText(rentModel.rent + "");
        listMonth.setText(rentModel.month);

//            // Return the completed view to render on screen
        return convertView;

    }
    public CustomRentAdapter(Context context, ArrayList<RentModel> rent) {
        super(context, 0, rent);
    }
}
