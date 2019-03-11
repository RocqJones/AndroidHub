package com.rocqjones.intoverflown.listviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView myListView = (ListView) findViewById(R.id.myListView);

        //create an array list of names
        final ArrayList<String> myFamily = new ArrayList<String>();
        myFamily.add("Rocq");
        myFamily.add("Jones");
        myFamily.add("Tommy");
        myFamily.add("Annastacia");

        //concerting it to a ListView using array adaptor
        ArrayAdapter<String> arrAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myFamily);
        myListView.setAdapter(arrAdapter);

        //creatinng action listener for the ListView
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //if you want the table dissapear when the user taps  we use 'parent' to represent the table
                Log.i("Person Tapped: ", myFamily.get(position));

                //Adding toast code
                Toast.makeText(getApplicationContext(), "Hello " + myFamily.get(position) + "!!!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
