package com.rocqjones.intoverflown.animations;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    //method fade
    public void fade(View view){
        ImageView bart = (ImageView) findViewById(R.id.bartImg);
        ImageView homer = (ImageView) findViewById(R.id.homerImg);

        //to fade it.. Alpha value indicates how the image is visible. 0f will make it invisible
        bart.animate().alpha(0f).setDuration(2000);
        homer.animate().alpha(1f).setDuration(2000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}
