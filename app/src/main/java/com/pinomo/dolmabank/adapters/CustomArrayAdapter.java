package com.pinomo.dolmabank.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pinomo.dolmabank.R;

/**
 * Custom ArrayAdapter for the Spinner in the MainActivity
 */
public class CustomArrayAdapter extends ArrayAdapter<String> {
    private Context mContext;
    private String[] mLanguages;
    private int mSelectedPosition;

    /**
     * Constructor
     *
     * @param context   - Context
     * @param resource  - Resource ID
     * @param languages - Array of languages
     */
    public CustomArrayAdapter(Context context, int resource, String[] languages) {
        super(context, resource, languages);
        mContext = context;
        mLanguages = languages;
        mSelectedPosition = -1;

    }

    /**
     * Set the selected position
     *
     * @param position - Position to be selected
     */
    public void setSelectedPosition(int position) {
        mSelectedPosition = position;
        notifyDataSetChanged();
    }

    /**
     * Get the selected position
     * @param position The position of the item within the adapter's data set of the item whose view
     *        we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *        is non-null and of an appropriate type before using. If it is not possible to convert
     *        this view to display the correct data, this method can create a new view.
     *        Heterogeneous lists can specify their number of view types, so that this View is
     *        always of the right type (see {@link #getViewTypeCount()} and
     *        {@link #getItemViewType(int)}).
     * @param parent The parent that this view will eventually be attached to
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }


    /**
     * Get a View that displays in the drop down popup the data at the specified position in the data
     * @param position index of the item whose view we want.
     * @param convertView the old view to reuse, if possible. Note: You should
     *        check that this view is non-null and of an appropriate type before
     *        using. If it is not possible to convert this view to display the
     *        correct data, this method can create a new view.
     * @param parent the parent that this view will eventually be attached to
     * @return
     */

    /**
     * Get a View that displays in the drop down popup the data at the specified position in the data
     * @param position index of the item whose view we want.
     * @param convertView the old view to reuse, if possible. Note: You should
     *        check that this view is non-null and of an appropriate type before
     *        using. If it is not possible to convert this view to display the
     *        correct data, this method can create a new view.
     * @param parent the parent that this view will eventually be attached to
     * @return A view corresponding to the data at the specified position.
     */
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    /**
     * Get a View that displays in the drop down popup the data at the specified position in the data
     *
     * @param position    index of the item whose view we want.
     * @param convertView the old view to reuse, if possible. Note: You should        check that this view is non-null and of an appropriate type before        using. If it is not possible to convert this view to display the        correct data, this method can create a new view.
     * @param parent      the parent that this view will eventually be attached to
     * @return A view corresponding to the data at the specified position.
     */
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
