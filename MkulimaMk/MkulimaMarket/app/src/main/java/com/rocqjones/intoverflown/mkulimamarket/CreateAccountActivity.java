package com.rocqjones.intoverflown.mkulimamarket;

import android.app.ProgressDialog;
import android.content.Intent;
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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class CreateAccountActivity extends AppCompatActivity {

    private EditText Remail,Rpassword,Rusername;
    private Button RegisterBtn;

    private FirebaseAuth mAuth;
    private String CurrentUserID;
    private ProgressDialog progressDialog;

    private DatabaseReference UsersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        mAuth = FirebaseAuth.getInstance();


        RegisterBtn = (Button) findViewById(R.id.register_button);
        Remail = (EditText) findViewById(R.id.register_email);
        Rpassword = (EditText) findViewById(R.id.register_password);
        Rusername = (EditText) findViewById(R.id.register_username);

        progressDialog = new ProgressDialog(this);

        RegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                LoginUser();
            }
        });
    }



    private void LoginUser() {
        String useremail =  Remail.getText().toString();
        String userpassword = Rpassword.getText().toString();
        final String userusername = Rusername.getText().toString();
       if(TextUtils.isEmpty(useremail)) {
           Toast.makeText(this, "email is required", Toast.LENGTH_SHORT).show();
       }
       else  if(TextUtils.isEmpty(userpassword)) {
           Toast.makeText(this, "password is required", Toast.LENGTH_SHORT).show();
       }
       else  if(TextUtils.isEmpty(userusername)) {
           Toast.makeText(this, "username is required", Toast.LENGTH_SHORT).show();
       }


        else {
            progressDialog.setTitle("Creating");
            progressDialog.setMessage("please wait .. ");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();


            mAuth.createUserWithEmailAndPassword(useremail,userpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    //Task -  It's a group of related activities, arranged in a stack.
                    if(task.isSuccessful()) {

                        CurrentUserID = mAuth.getCurrentUser().getUid();
                        UsersRef = FirebaseDatabase.getInstance().getReference().child("Users").child(CurrentUserID);


                        Map userMap = new HashMap();
                        userMap.put("id",CurrentUserID);
                        userMap.put("name",userusername);

                        UsersRef.updateChildren(userMap).addOnCompleteListener(new OnCompleteListener() {
                            @Override
                            public void onComplete(@NonNull Task task) {

                                progressDialog.dismiss();
                                Toast.makeText(CreateAccountActivity.this, "Account Created Successfully", Toast.LENGTH_SHORT).show();

                                Intent a = new Intent(CreateAccountActivity.this, LoginActivity.class);
                                startActivity(a);

                            }
                        });
                    }
                    else {
                        progressDialog.dismiss();
                        String message  = task.getException().toString();
                        Toast.makeText(CreateAccountActivity.this, "Try again: " + message, Toast.LENGTH_LONG).show();

                        Remail.setText("");
                        Rpassword.setText("");
                        Rusername.setText("");
                    }
                }
            });
        }
    }
}
