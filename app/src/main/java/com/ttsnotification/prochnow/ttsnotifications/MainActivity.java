package com.ttsnotification.prochnow.ttsnotifications;

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

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    private final String LOG_TAG = MainActivity.class.getSimpleName();


    @InjectView(R.id.drawerLayout) DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    @InjectView(R.id.toolbar) Toolbar toolbar;
    @InjectView(R.id.navigation) NavigationView navigation;


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

        doFragmentTransition(new HomeFragment(), getString(R.string.homeTitle));


        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                drawerLayout.closeDrawers();
                switch (id) {
                    case R.id.homeMenu:

                        doFragmentTransition(new HomeFragment(), getString(R.string.homeTitle));

                        break;
                    case R.id.appMenu:
                        doFragmentTransition(new AppFragment(), getString(R.string.appTitle));
                        break;
                    case R.id.wifiMenu:

                        doFragmentTransition(new WifiFragment(), getString(R.string.wifiTitle));
                        break;
                    case R.id.locationMenu:
                        doFragmentTransition(new LocationFragment(), getString(R.string.locationTitle));
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
