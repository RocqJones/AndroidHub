package com.rocqjones.intoverflown.logindemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void login(View view){ //it's void because we won't return anything
        //after assigning we convert into a EditText
        EditText username = (EditText) findViewById(R.id.username);

        EditText password = (EditText) findViewById(R.id.password);

        //let's log some information
        Log.i("username", username.getText().toString());
        Log.i("password", password.getText().toString());

        //new click function called "toast"- Displays a text and dissapears after a while e.g "login successfull"
        //Toast.makeText(getApplicationContext(), "login successful!", Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(), "login successful " + username.getText().toString(), Toast.LENGTH_LONG).show();
        //NOTE for this to work you need to parse method "login" of main activity to attribute 'onClick'
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
