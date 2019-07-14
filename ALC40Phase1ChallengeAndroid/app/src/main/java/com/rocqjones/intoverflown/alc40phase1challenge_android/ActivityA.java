package com.rocqjones.intoverflown.alc40phase1challenge_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ActivityA extends AppCompatActivity {

    Button btnb, btnc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);

        btnb = (Button) findViewById(R.id.aboutBtn);
        btnb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b = new Intent(getApplicationContext(), ActivityB.class);
                startActivity(b);
            }
        });

        btnc = (Button) findViewById(R.id.profileBtn);
        btnc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent c = new Intent(getApplicationContext(), ActivityC.class);
                startActivity(c);
            }
        });
    }

}
