package com.pinomo.dolmabank.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import  android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.pinomo.dolmabank.MainActivity;
import com.pinomo.dolmabank.R;
import com.pinomo.dolmabank.adapters.CustomArrayAdapter;
import com.pinomo.dolmabank.databinding.RegisterFragmentBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;


public class RegisterFragment extends Fragment {
    @NonNull
    public RegisterFragmentBinding getBinding() {
        assert _binding != null;
        return _binding;
    }

    @Nullable
    private RegisterFragmentBinding _binding = null;

    final Calendar myCalendar = Calendar.getInstance();

    public RegisterFragment() {
        super(R.layout.register_fragment);
    }

    private int getIconResource(String selectedItem) {
        return switch (selectedItem) {
            case "Russian" -> R.drawable.ic_russian;
            case "English" -> R.drawable.ic_english;
            case "Ukrainian" -> R.drawable.ic_ukrainian;
            case "Azerbaijani" -> R.drawable.ic_azerbaijani;
            case "French" -> R.drawable.ic_french;
            case "Deutsch" -> R.drawable.ic_deutch;
            default -> R.drawable.ic_english; // Provide a default icon if the selected item doesn't have an associated icon
        };
    }
    String[] languages= {"Russian","English","Ukrainian","Azerbaijani","French","Deutsch"};

    AutoCompleteTextView autoCompleteTxt;

    ArrayAdapter<String> adapterLanguages;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        _binding = RegisterFragmentBinding.inflate(inflater, container, false);
        ConstraintLayout view = getBinding().getRoot();

        autoCompleteTxt = getBinding().autoCompleteTxt;
        adapterLanguages = new CustomArrayAdapter(getContext(), R.layout.dropdown_item_layout, languages);
        autoCompleteTxt.setAdapter(adapterLanguages);
        autoCompleteTxt.setOnItemClickListener((parent, view1, position, id) -> {
            String item = parent.getItemAtPosition(position).toString();
            ImageView languageIcon = parent.findViewById(R.id.language_icon);
            int iconResId = getIconResource(item);
            autoCompleteTxt.setCompoundDrawablesWithIntrinsicBounds(iconResId,0,0,0);


            languageIcon.setImageResource(iconResId);


            Toast.makeText(getContext(),"Language:"+iconResId,Toast.LENGTH_SHORT).show();
        });

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR,year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                updateLabel();
            }
        };

        getBinding().birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(requireActivity(),date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        return view;
    }

    private void updateLabel(){
        String myFormat="MM/dd/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.getDefault());
        getBinding().birthday.setText(dateFormat.format(myCalendar.getTime()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        _binding = null;
    }
}