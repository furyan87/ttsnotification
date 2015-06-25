package com.prochnow.ttsnotifications;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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

import com.prochnow.ttsnotifications.app.AppFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    private final String LOG_TAG = MainActivity.class.getSimpleName();

    private enum Screens {HOME, APP, WIFI, LOCATION}

    private static Screens ACTIVE_SCREEN = Screens.HOME;


    @InjectView(R.id.drawerLayout) DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    @InjectView(R.id.toolbar) Toolbar toolbar;
    @InjectView(R.id.navigation) NavigationView navigation;
    @InjectView(R.id.fabBtn) FloatingActionButton fabButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //TODO 23.06.15 implement theme switching 
        //setTheme(R.style.AppThemeLight_WithoutActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ButterKnife.inject(this);
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
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
        drawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, R.string.hello_world, R.string.hello_world);
        drawerLayout.setDrawerListener(drawerToggle);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        switch (ACTIVE_SCREEN) {
            case HOME:
                HomeFragment fragment = new HomeFragment();
                fragment.applyFABButton(fabButton);
                doFragmentTransition(fragment, getString(R.string.homeTitle));
                break;
            case APP:
                AppFragment appFragment = new AppFragment();
                appFragment.applyFABButton(fabButton);
                doFragmentTransition(appFragment, getString(R.string.appTitle));
                break;
            case WIFI:

                doFragmentTransition(new WifiFragment(), getString(R.string.wifiTitle));
                break;
            case LOCATION:
                doFragmentTransition(new LocationFragment(), getString(R.string.locationTitle));
                break;
        }


        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                drawerLayout.closeDrawers();

                switch (id) {
                    case R.id.homeMenu:
                        HomeFragment fragment = new HomeFragment();
                        fragment.applyFABButton(fabButton);
                        doFragmentTransition(fragment, getString(R.string.homeTitle));
                        ACTIVE_SCREEN = Screens.HOME;
                        break;
                    case R.id.appMenu:
                        AppFragment appFragment = new AppFragment();
                        appFragment.applyFABButton(fabButton);
                        doFragmentTransition(appFragment, getString(R.string.appTitle));
                        ACTIVE_SCREEN = Screens.APP;

                        break;
                    case R.id.wifiMenu:

                        doFragmentTransition(new WifiFragment(), getString(R.string.wifiTitle));
                        ACTIVE_SCREEN = Screens.WIFI;
                        break;
                    case R.id.locationMenu:
                        doFragmentTransition(new LocationFragment(), getString(R.string.locationTitle));
                        ACTIVE_SCREEN = Screens.LOCATION;
                        break;
                }
                return false;
            }
        });
    }

    private void doFragmentTransition(Fragment fragment, String newActionBarTitle) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        getSupportActionBar().setTitle(newActionBarTitle);
        fragmentTransaction.replace(R.id.contentArea, fragment, null);
        fragmentTransaction.commit();
    }


}
