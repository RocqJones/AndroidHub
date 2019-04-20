package com.rocqjones.intoverflown.mkulimamarket;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class Report extends AppCompatActivity {

    TextView numberOfUsers, numberOfFarmers, numberOfConsumers, inflatedProduct;

    private FirebaseAuth mAuth;
    private String CurrentUserID;
    private DatabaseReference UsersR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        //call id from firebase
        mAuth = FirebaseAuth.getInstance();

        //call if from report xml
        numberOfUsers = (TextView) findViewById(R.id.numberOfUsers);
        inflatedProduct = (TextView) findViewById(R.id.inflatedProduct);
        numberOfFarmers = (TextView) findViewById(R.id.numberOfFarmers);
        numberOfConsumers = (TextView) findViewById(R.id.numberOfConsumers);

        //No. users
        int numOfUSers = 6;
        numberOfUsers.setText("" + numOfUSers);

        //No. farmers
        int numOfFarmers = ((numOfUSers/2)+1);
        numberOfFarmers.setText("" + numOfFarmers);

        //No. of consumers
        int numOfConsumers = (numOfUSers - numOfFarmers);
        numberOfConsumers.setText("" + numOfConsumers);

        inflatedProduct.setText("CowPeas");
    }

}
