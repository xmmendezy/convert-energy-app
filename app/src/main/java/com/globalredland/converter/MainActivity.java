package com.globalredland.converter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.globalredland.converter.database.AppDatabase;
import com.globalredland.converter.database.FuelEntry;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.globalredland.converter.ui.converter.ConverterFragment;
import com.globalredland.converter.ui.equivalente.EquivalenteFragment;
import com.globalredland.converter.ui.gallery.GalleryFragment;
import com.globalredland.converter.ui.home.HomeFragment;
import com.globalredland.converter.ui.intro.IntroActivity;
import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppDatabase mDb;
    private AppBarConfiguration mAppBarConfiguration;

    String prevStarted = "prevStarted";

    Dialog myDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDialog = new Dialog(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);


        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_converter, R.id.nav_equivalente)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        mDb = AppDatabase.getInstance(getApplicationContext());
        mDb.fuelDao().insertAllFuels(FuelEntry.populateData());

//        Intent intent = new Intent(this, IntroActivity.class);
//        startActivity(intent);
        navigationView.setNavigationItemSelectedListener(this);

    }

    public void ShowDialog(){
        TextView textClose;
        Button btnClose;

        myDialog.setContentView(R.layout.about_dialog);
        textClose = myDialog.findViewById(R.id.txtClose);
        TextView textLink = myDialog.findViewById(R.id.txtLink);

        textClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        textLink.setMovementMethod(LinkMovementMethod.getInstance());
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sharedpreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        if (!sharedpreferences.getBoolean(prevStarted, false)) {

            Intent intent = new Intent(this, IntroActivity.class);
            startActivity(intent);

            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putBoolean(prevStarted, Boolean.TRUE);
            editor.apply();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        Fragment fragment = null;

        int id = menuItem.getItemId();

        if (id == R.id.nav_home){
            fragment = new HomeFragment();
            displaySelectedFragment(fragment);
        } else if (id == R.id.nav_converter){
            fragment = new ConverterFragment();
            displaySelectedFragment(fragment);
        } else if (id == R.id.nav_equivalente){
            fragment = new EquivalenteFragment();
            displaySelectedFragment(fragment);
        } else if (id == R.id.nav_gallery){
            fragment = new GalleryFragment();
            displaySelectedFragment(fragment);
        } else if (id == R.id.nav_slideshow){
            Intent intent = new Intent(this, IntroActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_about){
            ShowDialog();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void displaySelectedFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
        fragmentTransaction.commit();
    }
}
