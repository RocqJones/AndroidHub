package com.rocqjones.intoverflown.mynotes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import java.util.HashSet;

public class EditYourNote extends AppCompatActivity implements TextWatcher {

    int noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_your_note);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Not yet configured", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //editText
        EditText editText = (EditText) findViewById(R.id.editText);
        Intent i = getIntent();
        noteId = i.getIntExtra("noteId", -1);

        if (noteId != -1) {
            editText.setText(MainActivity.notes.get(noteId));
        }

        //add text lister
        editText.addTextChangedListener(this); //the casted parameters of methods will be found below
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

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        //this updates notes
        MainActivity.notes.set(noteId, String.valueOf(s)); //s is the new location to store the text converted to string
        //after updating notes we need to update arrayAdapter as well and make sure it's a stratic variable
        MainActivity.arrayAdapter.notifyDataSetChanged();

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.rocqjones.intoverflown.mynotes", Context.MODE_PRIVATE);

        if (MainActivity.set == null) {
            MainActivity.set = new HashSet<String>();
        } else {
            MainActivity.set.clear();
        }

        MainActivity.set.addAll(MainActivity.notes);
        //take shared preferences and remove the set you want to update
        sharedPreferences.edit().remove("notes").apply();
        //when you put a StringSet over an existing stringSet it does not work. So we remove the set we want to remove with line above
        sharedPreferences.edit().putStringSet("notes", MainActivity.set).apply();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
