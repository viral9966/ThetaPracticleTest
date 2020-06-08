package com.example.thetapracticletest;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import com.example.thetapracticletest.ui.home.HomeFragment;
import com.example.thetapracticletest.ui.map.MapFragment;
import com.example.thetapracticletest.ui.profile.ProfileFragment;
import com.example.thetapracticletest.utils.AppConstants;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.List;

public class NavigationDrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TabBarController controller;
    DrawerLayout drawer;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        setUpTabBar();
    }

    private void setUpTabBar() {
        TabBarView bottomView = (TabBarView) findViewById(R.id.tabbarview);

        TabBarController.TabBuilder tabBuilder = TabBarController.TabBuilder.INSTANCE;
        List<TabBarController.Tab> tabs = tabBuilder
                .addTab(AppConstants.SetTitleHome, ContextCompat.getDrawable(this, R.drawable.ic_home_white_24dp), ContextCompat.getDrawable(this, R.drawable.tab_background_selected), ContextCompat.getDrawable(this, R.drawable.tab_background_deselected))
                .addTab(AppConstants.SetTitleMap, ContextCompat.getDrawable(this, R.drawable.ic_explore_map_24dp), ContextCompat.getDrawable(this, R.drawable.tab_background_selected), ContextCompat.getDrawable(this, R.drawable.tab_background_deselected))
                .addTab(AppConstants.SetTitleProfile, ContextCompat.getDrawable(this, R.drawable.ic_user_white_24dp), ContextCompat.getDrawable(this, R.drawable.tab_background_selected), ContextCompat.getDrawable(this, R.drawable.tab_background_deselected))
                .build();

        controller = new TabBarController(this, tabs, bottomView);
        controller.setCurrentItem(0);
        controller.setListener(new TabBarListener() {
            @Override
            public void pageHasBeenChanged(int position) {
                //Change fragments here or... or something
                if (position == 1) {
                    navigationView.setCheckedItem(R.id.nav_map);
                    setFragment(new MapFragment(),getString(R.string.menu_map));

                } else if (position == 2){
                    navigationView.setCheckedItem(R.id.nav_profile);
                    setFragment(new ProfileFragment(),getString(R.string.menu_profile));
                } else {
                    navigationView.setCheckedItem(R.id.nav_home);
                    setFragment(new HomeFragment(),getString(R.string.menu_home));
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.nav_home) {
            controller.setCurrentItem(0);
            setFragment(new HomeFragment(),getString(R.string.menu_home));
        } else if (item.getItemId() == R.id.nav_map) {
            controller.setCurrentItem(1);
            setFragment(new MapFragment(),getString(R.string.menu_map));
        } else {
            controller.setCurrentItem(2);
            setFragment(new ProfileFragment(),getString(R.string.menu_profile));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setFragment(Fragment fragment,String getTitle) {
        toolbar.setTitle(getTitle);
        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.addToBackStack(null);
            ft.commit();
        }
    }
}
