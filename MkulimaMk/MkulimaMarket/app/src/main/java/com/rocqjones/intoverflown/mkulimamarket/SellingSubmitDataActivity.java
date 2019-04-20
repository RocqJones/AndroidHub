package com.rocqjones.intoverflown.mkulimamarket;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SellingSubmitDataActivity extends AppCompatActivity {

    private Button mSendButton;

    // Firebase instance variables
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mProductDatabaseReference;

    public EditText sellerName, quantityPrice, productName, productQuntity,location;

    private String CurrentUserID;
    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference UsersRef,ProductsRef;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sellingsubmitdata);

        // Initialize Firebase components
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mProductDatabaseReference = mFirebaseDatabase.getReference().child("products");


        UsersRef = FirebaseDatabase.getInstance().getReference().child("Users");
        ProductsRef = FirebaseDatabase.getInstance().getReference().child("products");



        //initialize product's text here from layout
        sellerName = findViewById(R.id.sellersNameEdit);
        quantityPrice = findViewById(R.id.sellingPriceText);
        productName = findViewById(R.id.productEdNameText);
        productQuntity = findViewById(R.id.productQuantityEd);
        location = findViewById(R.id.sellersNameEdit);


        mSendButton = (Button) findViewById(R.id.mSendButton);

        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SaveProducts();
                final String sellername = sellerName.getText().toString();
                final String price = quantityPrice.getText().toString();
                final String qty = productQuntity.getText().toString();
                final String pname = productName.getText().toString();
                final String place = location.getText().toString();
                if(TextUtils.isEmpty(price)) {
                    Toast.makeText(SellingSubmitDataActivity.this, "price is required", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(qty)) {
                    Toast.makeText(SellingSubmitDataActivity.this, "product qty is required", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(pname)) {
                    Toast.makeText(SellingSubmitDataActivity.this, "product name is required", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(place)) {
                    Toast.makeText(SellingSubmitDataActivity.this, "Location is required", Toast.LENGTH_SHORT).show();
                }
                else {
                    //CurrentUserID = mFirebaseAuth.getInstance().getCurrentUser().getUid();
                    UsersRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.exists()) {
                                String name = dataSnapshot.child("name").getValue().toString();

                                Date date = new Date();
                                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                                String strDate = formatter.format(date);

                                Map productMap = new HashMap();
                                productMap.put("sellername",name);
                                productMap.put("productPrice",price);
                                productMap.put("productQty",qty);
                                productMap.put("productName",pname);
                                productMap.put("date",strDate);
                                productMap.put("location",place);
                                ProductsRef.push().updateChildren(productMap).addOnCompleteListener(new OnCompleteListener() {
                                    @Override
                                    public void onComplete(@NonNull Task task) {
                                        if(task.isSuccessful()) {
                                            Toast.makeText(SellingSubmitDataActivity.this, pname+ " saved successfully", Toast.LENGTH_SHORT).show();
                                            finish();
                                        }
                                    }
                                });
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });
                }
            }
        });
    }
}