package com.pinomo.dolmabank.fragments;

import static androidx.navigation.fragment.FragmentKt.findNavController;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.pinomo.dolmabank.R;
import com.pinomo.dolmabank.databinding.RegisterLandingFragmentBinding;

public class RegisterLandingFragment extends Fragment {
    @NonNull
    public RegisterLandingFragmentBinding getBinding() {
        assert _binding != null;
        return _binding;
    }

    @Nullable
    private RegisterLandingFragmentBinding _binding = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        _binding = RegisterLandingFragmentBinding.inflate(inflater, container, false);
        View view = _binding.getRoot();

        getBinding().button.setOnClickListener(v -> {
            findNavController(this).navigate(R.id.action_registerLandingFragment_to_registerFragment);
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        _binding = null;
    }
}
