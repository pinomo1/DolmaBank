package com.pinomo.dolmabank.fragments;

import static androidx.navigation.fragment.FragmentKt.findNavController;

import static com.pinomo.dolmabank.utils.LanguageUtils.getIconResource;
import static com.pinomo.dolmabank.utils.LanguageUtils.getLanguageCode;
import static com.pinomo.dolmabank.utils.LanguageUtils.getLanguages;
import static com.pinomo.dolmabank.utils.LanguageUtils.setLanguage;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;

import  android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.pinomo.dolmabank.DolmaBankApp;
import com.pinomo.dolmabank.MainActivity;
import com.pinomo.dolmabank.R;
import com.pinomo.dolmabank.adapters.CustomArrayAdapter;
import com.pinomo.dolmabank.database.LocalUser;
import com.pinomo.dolmabank.database.LocalUserDao;
import com.pinomo.dolmabank.databinding.RegisterFragmentBinding;
import com.pinomo.dolmabank.models.User;
import com.pinomo.dolmabank.utils.AppUtils;
import com.pinomo.dolmabank.utils.LanguageUtils;
import com.pinomo.dolmabank.utils.crypto.CryptoUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;


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

    String[] languages = getLanguages();
    String selectedLanguage;

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
            selectedLanguage = item;
            ImageView languageIcon = parent.findViewById(R.id.language_icon);
            int iconResId = getIconResource(item);
            autoCompleteTxt.setCompoundDrawablesWithIntrinsicBounds(iconResId,0,0,0);
            setLanguage(requireActivity(), getLanguageCode(item));
            languageIcon.setImageResource(iconResId);
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

        NavController navController = findNavController(this);
        getBinding().button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getBinding().name.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Please enter your name", Toast.LENGTH_SHORT).show();
                    return;
                }

                Calendar today = Calendar.getInstance();
                int age = today.get(Calendar.YEAR) - myCalendar.get(Calendar.YEAR);
                int todayMonth = today.get(Calendar.MONTH);
                int todayDay = today.get(Calendar.DAY_OF_MONTH);
                int myCalendarYear = myCalendar.get(Calendar.YEAR);
                int myCalendarMonth = myCalendar.get(Calendar.MONTH);
                int myCalendarDay = myCalendar.get(Calendar.DAY_OF_MONTH);
                if (todayMonth < myCalendarMonth || (todayMonth == myCalendarMonth && todayDay < myCalendarDay)) {
                    age--;
                }
                if (age < 18) {
                    Toast.makeText(getContext(), "You must be 18+ to register", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (selectedLanguage == null) {
                    Toast.makeText(getContext(), "Please select a language", Toast.LENGTH_SHORT).show();
                    return;
                }

                String name = getBinding().name.getText().toString();

                User user = new User();
                user.name = CryptoUtils.Companion.encrypt(name);
                user.yearOfBirth = myCalendarYear;
                user.monthOfBirth = myCalendarMonth;
                user.dayOfBirth = myCalendarDay;
                LocalUser localUser = new LocalUser();
                localUser.user = user;
                DolmaBankApp app = (DolmaBankApp) requireActivity().getApplication();
                LocalUserDao userDao = app.getDb().getLocalUserDao();
                userDao.insert(localUser);
                Log.i("RegisterFragment", "Insertion complete");
                AppUtils.setFirstLaunch(requireActivity(), false);
                LanguageUtils.setLanguage(requireActivity(), getLanguageCode(selectedLanguage));
                navController.navigate(R.id.action_registerFragment_to_homescreenFragment);
            }
        });

        return view;
    }

    private void updateLabel(){
        String myFormat="dd/MM/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.getDefault());
        getBinding().birthday.setText(dateFormat.format(myCalendar.getTime()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        _binding = null;
    }
}