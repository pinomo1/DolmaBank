package com.pinomo.dolmabank.fragments;

import static com.pinomo.dolmabank.utils.LanguageUtils.getIconResource;
import static com.pinomo.dolmabank.utils.LanguageUtils.getLanguageCode;
import static com.pinomo.dolmabank.utils.LanguageUtils.getLanguages;
import static com.pinomo.dolmabank.utils.LanguageUtils.saveLanguage;
import static com.pinomo.dolmabank.utils.LanguageUtils.setLanguage;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.pinomo.dolmabank.DolmaBankApp;
import com.pinomo.dolmabank.R;
import com.pinomo.dolmabank.adapters.CustomArrayAdapter;
import com.pinomo.dolmabank.database.LocalUser;
import com.pinomo.dolmabank.database.LocalUserDao;
import com.pinomo.dolmabank.databinding.ProfileFragmentBinding;
import com.pinomo.dolmabank.models.User;
import com.pinomo.dolmabank.utils.LanguageUtils;
import com.pinomo.dolmabank.utils.crypto.CryptoUtils;

/**
 * Fragment for the profile page
 */
public class ProfileFragment extends Fragment {
    /**
     * Gets binding.
     *
     * @return the binding
     */
    @NonNull
    public ProfileFragmentBinding getBinding() {
        assert _binding != null;
        return _binding;
    }

    /**
     * Instantiates a new Profile fragment.
     */
    public ProfileFragment() {
        super(R.layout.profile_fragment);
    }

    @Nullable
    private ProfileFragmentBinding _binding = null;

    private boolean isEditing = false;

    /**
     * The Languages.
     */
    String[] languages = getLanguages();
    /**
     * The Selected language.
     */
    String selectedLanguage;

    /**
     * The Auto complete txt.
     */
    AutoCompleteTextView autoCompleteTxt;

    /**
     * The Adapter languages.
     */
    ArrayAdapter<String> adapterLanguages;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        _binding = ProfileFragmentBinding.inflate(inflater, container, false);
        View view = _binding.getRoot();

        getBinding().changeSaveBtn.setOnClickListener(v -> {
            isEditing = !isEditing;
            getBinding().changeSaveBtn.setText(LanguageUtils.getString(requireActivity(), isEditing ? R.string.save : R.string.change));
            getBinding().nameField.setEnabled(isEditing);
            getBinding().emailField.setEnabled(isEditing);
            getBinding().phoneField.setEnabled(isEditing);
            getBinding().addressField.setEnabled(isEditing);

            if (!isEditing) {
                DolmaBankApp app = (DolmaBankApp) requireActivity().getApplication();
                LocalUserDao userDao = app.getDb().getLocalUserDao();
                LocalUser localUser = userDao.getFirst();
                User user = localUser.user;
                user.name = CryptoUtils.encrypt(getBinding().nameField.getText().toString());
                user.email = CryptoUtils.encrypt(getBinding().emailField.getText().toString());
                user.phoneNumber = CryptoUtils.encrypt(getBinding().phoneField.getText().toString());
                user.address = CryptoUtils.encrypt(getBinding().addressField.getText().toString());
                userDao.update(localUser);
            }
        });

        DolmaBankApp app = (DolmaBankApp) requireActivity().getApplication();
        LocalUserDao userDao = app.getDb().getLocalUserDao();
        LocalUser localUser = userDao.getFirst();
        User user = localUser.user;
        getBinding().nameField.setText(CryptoUtils.decrypt(user.name));
        if (user.email == null){
            user.email = "";
        }
        getBinding().emailField.setText(CryptoUtils.decrypt(user.email));
        if (user.phoneNumber == null){
            user.phoneNumber = "";
        }
        getBinding().phoneField.setText(CryptoUtils.decrypt(user.phoneNumber));
        if (user.address == null){
            user.address = "";
        }
        getBinding().addressField.setText(CryptoUtils.decrypt(user.address));

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
            saveLanguage(requireActivity(), getLanguageCode(item));
            try {
                languageIcon.setImageResource(iconResId);
            }
            catch (Exception e){
                Log.w("ProfileFragment", "Could not set language icon");
            }
        });

        String currentLanguage = LanguageUtils.getCurrentLanguage(requireActivity());
        ImageView languageIcon = autoCompleteTxt.findViewById(R.id.language_icon);
        int iconResId = getIconResource(currentLanguage);
        autoCompleteTxt.setCompoundDrawablesWithIntrinsicBounds(iconResId,0,0,0);
        autoCompleteTxt.setText(currentLanguage, false);
        try {
            languageIcon.setImageResource(iconResId);
        }
        catch (Exception e){
            Log.w("ProfileFragment", "Could not set language icon");
        }


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        _binding = null;
    }
}
