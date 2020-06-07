package com.example.thetapracticletest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabBarView bottomView = (TabBarView) findViewById(R.id.tabbarview);

        TabBarController.TabBuilder tabBuilder = TabBarController.TabBuilder.INSTANCE;
        List<TabBarController.Tab> tabs = tabBuilder
                .addTab("Home", ContextCompat.getDrawable(this, R.drawable.ic_home_white_24dp), ContextCompat.getDrawable(this, R.drawable.tab_background_selected), ContextCompat.getDrawable(this, R.drawable.tab_background_deselected))
                .addTab("Map", ContextCompat.getDrawable(this, R.drawable.ic_explore_map_24dp), ContextCompat.getDrawable(this, R.drawable.tab_background_selected), ContextCompat.getDrawable(this, R.drawable.tab_background_deselected))
                .addTab("Profile", ContextCompat.getDrawable(this, R.drawable.ic_user_white_24dp), ContextCompat.getDrawable(this, R.drawable.tab_background_selected), ContextCompat.getDrawable(this, R.drawable.tab_background_deselected))
                .build();

        TabBarController controller = new TabBarController(this, tabs, bottomView);
        controller.setListener(new TabBarListener() {
            @Override
            public void pageHasBeenChanged(int position) {
                //Change fragments here or... or something
            }
        });
        controller.setCurrentItem(0);
    }
}

