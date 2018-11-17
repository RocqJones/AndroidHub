package com.rocqjones.myjavaapplication;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.text.NumberFormat;

/*
* this app displays an order form from a coffee shop*/

public class MainActivity extends AppCompatActivity {

    //global variable quantity
    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*increment button*/
    public void increment(View view){
        quantity = quantity + 1;
        display(quantity);
    }

    /*decrement button*/
    public void decrement(View view){
        quantity = quantity - 1;
        display(quantity);
    }

    /*This method is called when an order is placed*/
    public void submitOrder(View view){
        displayPrice(quantity*5);
    }


    /*Thid method displays the bgiven quantity on the screen*/
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(""+ number);
    }

    /*displays given price on the screen*/
    private void displayPrice(int number){
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
}
