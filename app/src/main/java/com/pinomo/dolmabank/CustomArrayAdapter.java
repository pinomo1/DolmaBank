package com.example.dolmabank;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dolmabank.R;

public class CustomArrayAdapter extends ArrayAdapter<String> {
    private Context mContext;
    private String[] mLanguages;
    private int mSelectedPosition;
    public CustomArrayAdapter(Context context, int resource, String[] languages) {
        super(context, resource, languages);
        mContext = context;
        mLanguages = languages;
        mSelectedPosition = -1;

    }
    public void setSelectedPosition(int position) {
        mSelectedPosition = position;
        notifyDataSetChanged();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }


    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View row = inflater.inflate(R.layout.dropdown_item_layout, parent, false);


        TextView languageName = row.findViewById(R.id.language_name);
        ImageView languageIcon = row.findViewById(R.id.language_icon);

        languageName.setText(mLanguages[position]);
        // Set icon based on language
        switch (mLanguages[position]) {
            case "Russian":
                languageIcon.setImageResource(R.drawable.ic_russian);
                break;
            case "English":
                languageIcon.setImageResource(R.drawable.ic_english);
                break;
            case "Ukrainian":
                languageIcon.setImageResource(R.drawable.ic_ukrainian);
                break;
            case "Azerbaijani":
                languageIcon.setImageResource(R.drawable.ic_azerbaijani);
                break;
            case "French":
                languageIcon.setImageResource(R.drawable.ic_french);
                break;
            case "Deutsch":
                languageIcon.setImageResource(R.drawable.ic_deutch);
                break;    // Add more cases for other languages if needed
        }
        row.setBackgroundColor(mContext.getResources().getColor(R.color.menuColor));

        return row;
    }

}
