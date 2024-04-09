package com.example.fixit.helper_class;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fixit.R;
import com.example.fixit.expert_system.Problem;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends BaseAdapter {
    Context context;
    List<String> symptoms;
    int image;

    LayoutInflater inflater;

    public CustomAdapter(Context context, List<String> symptoms, int image){
        this.context = context;
        this.symptoms = symptoms;
        this.image = image;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return symptoms.toArray().length;
    }

    @Override
    public String getItem(int i) {
        return symptoms.toArray()[i].toString();
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = inflater.inflate(R.layout.symptom_list_item, null);
        TextView textView = (TextView) convertView.findViewById(R.id.item_name);
        ImageView imageView= (ImageView) convertView.findViewById(R.id.item_list_icon);

        textView.setText(symptoms.toArray()[position].toString());
        imageView.setImageResource(R.drawable.baseline_arrow_forward_ios_24);
        return convertView;
    }
}
