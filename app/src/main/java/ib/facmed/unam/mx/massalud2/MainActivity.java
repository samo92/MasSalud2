package ib.facmed.unam.mx.massalud2;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import ib.facmed.unam.mx.massalud2.ui.AboutUsFragment;
import ib.facmed.unam.mx.massalud2.ui.DashboardFragment;
import ib.facmed.unam.mx.massalud2.ui.FailFragment;
import ib.facmed.unam.mx.massalud2.ui.HomeFragment;

public class MainActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        //navigation.setSelectedItemId(R.id.navigation_home); // --> Metodo para definir el menu default al entrar a la actividad
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


}
