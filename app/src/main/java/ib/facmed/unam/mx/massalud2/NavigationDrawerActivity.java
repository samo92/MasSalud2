package ib.facmed.unam.mx.massalud2;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import ib.facmed.unam.mx.massalud2.ui.AboutUsFragment;
import ib.facmed.unam.mx.massalud2.ui.DashboardFragment;
import ib.facmed.unam.mx.massalud2.ui.FailFragment;
import ib.facmed.unam.mx.massalud2.ui.HomeFragment;
import ib.facmed.unam.mx.massalud2.ui.PostFragment;

public class NavigationDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        //navigation.setSelectedItemId(R.id.navigation_home); // --> Metodo para definir el menu default al entrar a la actividad
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_unam) {
            llamarPostFragment("23");

        } else if (id == R.id.nav_innovacion) {
            llamarPostFragment("21");

        } else if (id == R.id.nav_historia) {
            llamarPostFragment("19");

        } else if (id == R.id.nav_descubrir) {
            llamarPostFragment("17");

        } else if (id == R.id.nav_opinion) {
            llamarPostFragment("15");

        } else if (id == R.id.nav_actualidad) {
            llamarPostFragment("13");

        } else if (id == R.id.nav_global) {
            llamarPostFragment("10");

        } else if (id == R.id.nav_vistazos) {
            llamarPostFragment("8");

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void llamarPostFragment(String id){
        Bundle bundle = new Bundle();
        bundle.putString("id",id);
        AppCompatActivity appCompatActivity = this;
        PostFragment postFragment = new PostFragment();
        postFragment.setArguments(bundle);
        appCompatActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, postFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if (!haveNetworkConnection()) {
                        FailFragment failFragment = new FailFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.content, failFragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .addToBackStack(null).commit();
                    }else {
                        HomeFragment homeFragment = new HomeFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.content, homeFragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .addToBackStack(null).commit();
                    }
                    return true;
                case R.id.navigation_dashboard:
                    if (!haveNetworkConnection()) {
                        FailFragment failFragment = new FailFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.content, failFragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .addToBackStack(null).commit();
                    }else {
                        DashboardFragment dashboardFragment = new DashboardFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.content, dashboardFragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .addToBackStack(null).commit();
                    }

                    return true;
                case R.id.navigation_aboutus:
                    Bundle args = new Bundle();
                    args.putString("url","http://www.massaludfacmed.unam.mx/?page_id=5572#8243");
                    AboutUsFragment aboutUsFragment = new AboutUsFragment();
                    aboutUsFragment.setArguments(args);
                    getSupportFragmentManager().beginTransaction().replace(R.id.content, aboutUsFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .addToBackStack(null).commit();
                    return true;
            }
            return false;
        }

    };

    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }
}
