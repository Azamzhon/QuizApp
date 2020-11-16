package com.azamzhon.ui.main;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.azamzhon.R;
import com.azamzhon.ui.main.history.HistoryFragment;
import com.azamzhon.ui.main.home.HomeFragment;
import com.azamzhon.ui.main.settings.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private ViewPager viewPager;
    private List<Fragment> fragmentList;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        fillFragment();

        setupListeners();
    }

    private void setupListeners() {
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home_nav:
                    viewPager.setCurrentItem(0, false);
                    break;
                case R.id.history_nav:
                    viewPager.setCurrentItem(1, false);
                    break;
                case R.id.settings_nav:
                    viewPager.setCurrentItem(2, false);
                    break;
            }
            return true;
        });

        viewPager.setAdapter(new MainAdapter(fragmentList, getSupportFragmentManager()));
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
            }
        });
    }

    public void initialize() {
        bottomNavigationView = findViewById(R.id.main_bottom_nav);
        viewPager = findViewById(R.id.main_view_pager);
        fragmentList = new ArrayList<>();
    }

    private void fillFragment() {
        fragmentList.add(new HomeFragment());
        fragmentList.add(new HistoryFragment());
        fragmentList.add(new SettingsFragment());
    }
}