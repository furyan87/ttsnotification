package com.prochnow.ttsnotifications;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.prochnow.ttsnotifications.fragment.AppFragment;
import com.prochnow.ttsnotifications.fragment.HomeFragment;
import com.prochnow.ttsnotifications.fragment.LocationFragment;
import com.prochnow.ttsnotifications.fragment.WifiFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private final String LOG_TAG = MainActivity.class.getSimpleName();

    public enum Screens {HOME, APP, WIFI, LOCATION}

    public static Screens ACTIVE_SCREEN = Screens.HOME;


    @Bind(R.id.drawerLayout) DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.navigation) NavigationView navigation;
    //    @Bind(R.id.fabBtn) FloatingActionButton fabButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //TODO 23.06.15 implement theme switching 
        //setTheme(R.style.AppThemeLight_WithoutActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        initInstances();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds filteredList to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }


    private void initInstances() {
        drawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, R.string.openDrawer, R.string.closeDrawer);
        drawerLayout.setDrawerListener(drawerToggle);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        switch (ACTIVE_SCREEN) {
            case HOME:
                HomeFragment fragment = new HomeFragment();
                doFragmentTransition(fragment);
                break;
            case APP:
                AppFragment appFragment = new AppFragment();
                doFragmentTransition(appFragment);
                break;
            case WIFI:

                doFragmentTransition(new WifiFragment());
                break;
            case LOCATION:
                doFragmentTransition(new LocationFragment());
                break;
        }


        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();

                switch (id) {
                    case R.id.homeMenu:
                        HomeFragment fragment = new HomeFragment();
                        doFragmentTransition(fragment);
                        ACTIVE_SCREEN = Screens.HOME;
                        break;
                    case R.id.appMenu:
                        AppFragment appFragment = new AppFragment();
                        doFragmentTransition(appFragment);
                        ACTIVE_SCREEN = Screens.APP;
                        break;
                    case R.id.wifiMenu:
                        doFragmentTransition(new WifiFragment());
                        ACTIVE_SCREEN = Screens.WIFI;
                        break;
                    case R.id.locationMenu:
                        doFragmentTransition(new LocationFragment());
                        ACTIVE_SCREEN = Screens.LOCATION;
                        break;
                }
                return true;
            }
        });
    }

    private void doFragmentTransition(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contentArea, fragment, null);
        fragmentTransaction.commit();
        drawerLayout.closeDrawers();
    }


}
