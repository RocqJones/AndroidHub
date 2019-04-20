package com.rocqjones.intoverflown.mkulimamarket;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    private EditText email,password;
    private TextView neednewAccount_Link;
    private Button LoginBtn;

    private FirebaseAuth mAuth;
    private String CurrentUserID;
    private ProgressDialog progressDialog;

    //for setting username
    private RecyclerView view;
    private DatabaseReference productRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        LoginBtn = (Button) findViewById(R.id.login_button);
        email = (EditText) findViewById(R.id.login_email);
        password = (EditText) findViewById(R.id.login_password);
        neednewAccount_Link = (TextView) findViewById(R.id.create_account);
        progressDialog = new ProgressDialog(this);

        neednewAccount_Link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, CreateAccountActivity.class);
                startActivity(intent);

            }
        });

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   LoginUser();
                String loginuseremail =  email.getText().toString();
                String loginuserpassword = password.getText().toString();


                if(TextUtils.isEmpty(loginuseremail)) {
                    Toast.makeText(LoginActivity.this, "email required please", Toast.LENGTH_SHORT).show();

                }
                else  if(TextUtils.isEmpty(loginuserpassword)) {
                    Toast.makeText(LoginActivity.this, loginuseremail+ " password required please", Toast.LENGTH_SHORT).show();

                }
                else {
                    progressDialog.setTitle("Logging User");
                    progressDialog.setMessage("please wait .. ");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();

                    mAuth.signInWithEmailAndPassword(loginuseremail, loginuserpassword)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()) {
                                        Toast.makeText(LoginActivity.this, "Authentication success.",
                                                Toast.LENGTH_SHORT).show();

                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                        finish();


                                        //Set Username to the recyclerView
                                        productRef = FirebaseDatabase.getInstance().getReference().child("products");
                                        view = (RecyclerView) findViewById(R.id.recycleV);

                                    }
                                    else {
                                            String message = task.getException().toString();
                                        Toast.makeText(LoginActivity.this, "Authentication failed. " + message,
                                                Toast.LENGTH_SHORT).show();

                                        progressDialog.dismiss();
                                    }
                                }
                            });
                }
            }
        });
    }

    @Override
    protected void onStart()
    {
        FirebaseUser user = mAuth.getCurrentUser();
        super.onStart();

        if(user != null)
        {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();

        }
    }
}
