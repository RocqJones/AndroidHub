package com.rocqjones.intoverflown.mkulimamarket;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private FirebaseDatabase database;
    private DatabaseReference myRef,UsersRef;
    private StorageReference mStorageRef;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    private String CurrentUserID;

//    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
//            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment fragment = null;

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new HomeFragment();
                    FirebaseApp.initializeApp(this);
                    break;

                case R.id.navigation_dashboard:
                    fragment = new DashboardFragment();
                    break;

                case R.id.navigation_message:
                    fragment = new MessagesFragment();
                    break;
            }
            //return false;
            return loadFragment(fragment);
        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //loading the default fragment
        loadFragment(new HomeFragment());

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);


        //initialize app
        FirebaseApp.initializeApp(getApplicationContext());
        mFirebaseAuth = FirebaseAuth.getInstance();
        CurrentUserID = mFirebaseAuth.getCurrentUser().getUid();
        // Write a message to the database
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("products");

    }

    //Now we will switch the screens or fragments when the bottom navigation menu is clicked.
    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    //This method is necessitated for LogOut in menu_main when clicked to end session.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
            super.onOptionsItemSelected(item);
            if (item.getItemId() == R.id.logoutIC) {
                mFirebaseAuth.signOut();
                Intent loginscreen=new Intent(MainActivity.this, LoginActivity.class);
                loginscreen.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(loginscreen);
                finish();
            }
            else if (item.getItemId() == R.id.reportIt){
                //menu report
                Intent reportscreen=new Intent(MainActivity.this, Report.class);
                startActivity(reportscreen);
            }
            return true;
    }
}
