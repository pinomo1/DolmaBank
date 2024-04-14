package com.pinomo.dolmabank;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.view.View;
import android.view.WindowInsetsController;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pinomo.dolmabank.database.LocalUser;
import com.pinomo.dolmabank.database.LocalUserDao;
import com.pinomo.dolmabank.databinding.ActivityMainBinding;
import com.pinomo.dolmabank.utils.AppUtils;
import com.pinomo.dolmabank.utils.LanguageUtils;
import com.pinomo.dolmabank.utils.crypto.CryptoUtils;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private NavController navController;

    private void loadUserData(Consumer<LocalUser> callback) {
        DolmaBankApp app = (DolmaBankApp) this.getApplication();
        LocalUserDao userDao = app.getDb().getLocalUserDao();
        LocalUser user = userDao.getFirst();
        callback.accept(user);
    }

    private void controlNavBarVisibility(){
        List<Integer> noNavBarFragments = List.of(
                R.id.registerLandingFragment,
                R.id.registerFragment,
                R.id.loadingFragment,
                R.id.addCardFragment
        );

        BottomNavigationView navView = binding.navView;

        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if(noNavBarFragments.contains(destination.getId())){
                navView.setVisibility(View.GONE);
            }
            else {
                navView.setVisibility(View.VISIBLE);
            }
        });

        navView.setOnItemSelectedListener(
                item -> NavigationUI.onNavDestinationSelected(item, navController)
        );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
            }
        });
        CryptoUtils.Companion.loadCryptoFunction(this);

        LanguageUtils.setLanguage(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        assert navHostFragment != null;
        navController = navHostFragment.getNavController();

        customizeStatusBarAndTheme();

        boolean isFirstLaunch = AppUtils.isFirstLaunch(this);

        controlNavBarVisibility();
        if(savedInstanceState == null){
            if(isFirstLaunch){
                navController.navigate(R.id.action_loadingFragment_to_registerLandingFragment);
            }
            else {
                loadUserData(user -> {
                    if (user == null) {
                        navController.navigate(R.id.action_loadingFragment_to_registerLandingFragment);
                    } else {
                        navController.navigate(R.id.action_loadingFragment_to_homescreenFragment);
                    }
                });
            }
        }
    }

    private void customizeStatusBarAndTheme() {
        EdgeToEdge.enable(this);
//        getWindow().setFlags(
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
//        );
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            Objects.requireNonNull(getWindow().getInsetsController()).setSystemBarsAppearance(
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            );
        } else {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }
}