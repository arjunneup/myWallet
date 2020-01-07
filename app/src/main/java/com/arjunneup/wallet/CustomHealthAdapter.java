package com.arjunneup.wallet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomHealthAdapter extends ArrayAdapter<HealthModel> {

    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        // Get the data item for this position
        HealthModel healthModel = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view, parent, false);
        }
        // Lookup view for data population

        TextView listRent = (TextView) convertView.findViewById(R.id.listSalary);
        TextView listMonth = (TextView) convertView.findViewById(R.id.listMonth);

        // Populate the data into the template view using the data object
        listRent.setText(healthModel.health + "");
        listMonth.setText(healthModel.month);

//            // Return the completed view to render on screen
        return convertView;

    }

    public CustomHealthAdapter(Context context, ArrayList<HealthModel> health) {
        super(context, 0, health);
    }
}
