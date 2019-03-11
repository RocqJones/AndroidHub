package com.rocqjones.intoverflown.showingandhidingui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtView;

    public void show(View view) {
        txtView.setVisibility(View.VISIBLE);
    }

    public void hide(View view) {
        txtView.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //start
        txtView = (TextView) findViewById(R.id.txtViewId);
    }
}
