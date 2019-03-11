package com.rocqjones.intoverflown.mkulimamarket;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    //global id's for the ImageButton
    ImageButton maize, beans, cowpeas, tomatoes, potatoes, onions, fruits, coconuts, othernuts, carrots, greenpeas, fish, coffee, tea, rice,
                pyrethrum, sugarcane, sunflower, poutry;
    //GridLayout id
    GridLayout grid;

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                //home case
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    //hiding gridLayout on homepage
                    grid.setVisibility(View.INVISIBLE);
                    return true;

                //Dashboard case
                case R.id.navigation_dashboard:
                    //Here is where you add the GridLayout visible
                    grid.setVisibility(View.VISIBLE);
                    mTextMessage.setVisibility(View.INVISIBLE);
                    //mTextMessage.setText(R.string.title_dashboard);
                    return true;

                //Notification case
                case R.id.navigation_notifications:
                    //hiding gridLayout on notification
                    grid.setVisibility(View.INVISIBLE);
                    mTextMessage.setText(R.string.title_notifications);
                    return true;

                //Message case
                case R.id.navigation_message:
                    grid.setVisibility(View.INVISIBLE);
                    mTextMessage.setText(R.string.title_Messages);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


//        //add "+" icon
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        //calling all id here
        maize = (ImageButton) findViewById(R.id.maize);             beans = (ImageButton) findViewById(R.id.beans);
        cowpeas = (ImageButton) findViewById(R.id.cowpeas);         tomatoes = (ImageButton) findViewById(R.id.tomatoes);
        potatoes = (ImageButton) findViewById(R.id.potatoes);       onions = (ImageButton) findViewById(R.id.onions);
        fruits = (ImageButton) findViewById(R.id.fruits);           coconuts = (ImageButton) findViewById(R.id.coconuts);
        othernuts = (ImageButton) findViewById(R.id.othernuts);     carrots = (ImageButton) findViewById(R.id.carrots);
        greenpeas = (ImageButton) findViewById(R.id.greenpeas);     fish = (ImageButton) findViewById(R.id.fish);
        coffee = (ImageButton) findViewById(R.id.coffee);           tea = (ImageButton) findViewById(R.id.tea);
        rice = (ImageButton) findViewById(R.id.rice);               pyrethrum = (ImageButton) findViewById(R.id.pyrethrum);
        sugarcane = (ImageButton) findViewById(R.id.sugarcane);     sunflower = (ImageButton) findViewById(R.id.sunflower);
        poutry = (ImageButton) findViewById(R.id.poutry);

        //For GridLayout id
        grid = (GridLayout) findViewById(R.id.gridLId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.add) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //PRODUCT START (onClick) - Customization of each product ImageButton
    public void maize(View view){
        Toast.makeText(getApplicationContext(), "maize", Toast.LENGTH_LONG).show();
    }

    public void beans(View view){
        Toast.makeText(getApplicationContext(), "beans", Toast.LENGTH_LONG).show();
    }

    public void cowpeas(View view){
        Toast.makeText(getApplicationContext(), "cowpeas", Toast.LENGTH_LONG).show();
    }

    public void tomatoes(View view){
        Toast.makeText(getApplicationContext(), "tomatoes", Toast.LENGTH_LONG).show();
    }

    public void potatoes(View view){
        Toast.makeText(getApplicationContext(), "potatoes", Toast.LENGTH_LONG).show();
    }

    public void onions(View view){
        Toast.makeText(getApplicationContext(), "onions", Toast.LENGTH_LONG).show();
    }

    public void fruits(View view){
        Toast.makeText(getApplicationContext(), "fruits", Toast.LENGTH_LONG).show();
    }

    public void coconuts(View view){
        Toast.makeText(getApplicationContext(), "coconuts", Toast.LENGTH_LONG).show();
    }

    public void othernuts(View view){
        Toast.makeText(getApplicationContext(), "othernuts", Toast.LENGTH_LONG).show();
    }

    public void carrots(View view){
        Toast.makeText(getApplicationContext(), "carrots", Toast.LENGTH_LONG).show();
    }

    public void greenpeas(View view){
        Toast.makeText(getApplicationContext(), "greenpeas", Toast.LENGTH_LONG).show();
    }

    public  void fish(View view){
        Toast.makeText(getApplicationContext(), "fish", Toast.LENGTH_LONG).show();
    }

    public  void coffee(View view){
        Toast.makeText(getApplicationContext(), "coffee", Toast.LENGTH_LONG).show();
    }

    public  void tea(View view){
        Toast.makeText(getApplicationContext(), "tea", Toast.LENGTH_LONG).show();
    }

    public  void rice(View view){
        Toast.makeText(getApplicationContext(), "rice", Toast.LENGTH_LONG).show();
    }

    public  void pyrethrum(View view){
        Toast.makeText(getApplicationContext(), "pyrethrum", Toast.LENGTH_LONG).show();
    }

    public  void sugarcane(View view){
        Toast.makeText(getApplicationContext(), "sugarcane", Toast.LENGTH_LONG).show();
    }

    public  void sunflower(View view){
        Toast.makeText(getApplicationContext(), "sunflower", Toast.LENGTH_LONG).show();
    }

    public  void poutry(View view){
        Toast.makeText(getApplicationContext(), "poutry", Toast.LENGTH_LONG).show();
    }
    //END OF PRODUCTS
}
