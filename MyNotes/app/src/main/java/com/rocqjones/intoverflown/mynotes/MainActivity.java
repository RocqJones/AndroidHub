package com.rocqjones.intoverflown.mynotes;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    //lets create an array list for the notes.. we make it static variable in order to access it from the other class
    static ArrayList<String> notes = new ArrayList<>();
    static ArrayAdapter arrayAdapter;
    static Set<String> set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton ntAdd = findViewById(R.id.noteAdd);
        ntAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //this checks if it's clickable before adding any factionality

//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                //make it add note after click
                notes.add("");

                //replace 'this' with 'MainActivity.this'
                SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences("com.rocqjones.intoverflown.mynotes", Context.MODE_PRIVATE);

                if (set == null) {
                    set = new HashSet<String>();
                } else {
                    set.clear();
                }
                set.addAll(notes);
                sharedPreferences.edit().remove("notes").apply();
                sharedPreferences.edit().putStringSet("notes", set).apply();
                arrayAdapter.notifyDataSetChanged();
                Intent i = new Intent(getApplicationContext(), EditYourNote.class);
                i.putExtra("noteId", notes.size() - 1);
                startActivity(i);
            }
        });

        //litview ... start
        ListView listView = (ListView) findViewById(R.id.listView);

        //shared preferences
        SharedPreferences sharedPreferences = this.getSharedPreferences("com.rocqjones.intoverflown.mynotes", Context.MODE_PRIVATE);

        //Let restore 'set' in a array List
        set = sharedPreferences.getStringSet("notes", null);

        notes.clear();

        //check if we get some results then we can take our array list and add all item in a set, this will avoid data returning multiple times
        if (set != null) {
            notes.addAll(set);
        } else {
            //if not true
            notes.add("Example note");
            //let's initialize set to avoid crash
            set = new HashSet<String>();
            set.addAll(notes);
            sharedPreferences.edit().putStringSet("notes", set).apply();
        }

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, notes);
        listView.setAdapter(arrayAdapter);

        //this lister is for reasons when you click it takes you to the other view.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), EditYourNote.class);
                i.putExtra("noteId", position);
                startActivity(i);
            }
        });

        //add long clickListener here for deleting a note
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(MainActivity.this) //replace 'this' with 'MainActivity.this' to avoid crash
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Are you sure?")
                        .setMessage("Do you want to delete this?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //It will start deleting from 'notes'
                                notes.remove(position);

                                SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences("com.rocqjones.intoverflown.mynotes", Context.MODE_PRIVATE);

                                if (set == null) {
                                    set = new HashSet<String>();
                                } else {
                                    set.clear();
                                }
                                set.addAll(notes);
                                sharedPreferences.edit().remove("notes").apply(); //cp from EditYourNote class
                                sharedPreferences.edit().putStringSet("notes", set).apply();

                                //notify arrayAdapter after deleting
                                arrayAdapter.notifyDataSetChanged();

                            }
                        })
                        .setNegativeButton("No", null)
                        .show();

                return true;
            }
        });
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

            notes.add("");

            //copied from EdityourNote class
            SharedPreferences sharedPreferences = this.getSharedPreferences("com.rocqjones.intoverflown.mynotes", Context.MODE_PRIVATE);

            if (set == null) {
                set = new HashSet<String>();
            } else {
                set.clear();
            }
            set.addAll(notes);

            sharedPreferences.edit().remove("notes").apply();
            sharedPreferences.edit().putStringSet("notes", set).apply();

            //notify arrayAdapter after deleting
            arrayAdapter.notifyDataSetChanged();

            Intent i = new Intent(getApplicationContext(), EditYourNote.class);
            i.putExtra("noteId", notes.size() - 1);
            startActivity(i);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
