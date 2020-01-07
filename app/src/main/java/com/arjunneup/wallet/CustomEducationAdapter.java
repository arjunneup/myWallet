package com.arjunneup.wallet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.arjunneup.wallet.EducationModel;
import com.arjunneup.wallet.R;

import java.util.ArrayList;

public class CustomEducationAdapter extends ArrayAdapter<EducationModel> {

    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        // Get the data item for this position
        EducationModel educationModel = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view, parent, false);
        }
        // Lookup view for data population

        TextView listEducation = (TextView) convertView.findViewById(R.id.listSalary);
        TextView listMonth = (TextView) convertView.findViewById(R.id.listMonth);

        // Populate the data into the template view using the data object
        listEducation.setText(educationModel.education + "");
        listMonth.setText(educationModel.month);

//            // Return the completed view to render on screen
        return convertView;

    }

    public CustomEducationAdapter(Context context, ArrayList<EducationModel> education) {
        super(context, 0, education);
    }
}
