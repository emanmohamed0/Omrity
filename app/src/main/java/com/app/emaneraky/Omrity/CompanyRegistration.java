package com.app.emaneraky.Omrity;

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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class CompanyRegistration extends AppCompatActivity {
    Button RegistrationButton;
    EditText email_edit, name_edit, phone_edit, location_edit ,pass_edit;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseReference;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_registration);

        dialog = new ProgressDialog(this);

        RegistrationButton = (Button) findViewById(R.id.RegistrationButton);
        email_edit = (EditText) findViewById(R.id.emailEditText);
        name_edit = (EditText) findViewById(R.id.nameEditText);
        phone_edit = (EditText) findViewById(R.id.phoneEditText);
        location_edit = (EditText) findViewById(R.id.locationEditText);
        pass_edit = (EditText)findViewById(R.id.passEditText);

        RegistrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = email_edit.getText().toString();
                String name = name_edit.getText().toString();
                String phone = phone_edit.getText().toString();
                String location = location_edit.getText().toString();
                String password = pass_edit.getText().toString();


                if (!TextUtils.isEmpty(email) || !TextUtils.isEmpty(name) || !TextUtils.isEmpty(phone) || !TextUtils.isEmpty(location) || !TextUtils.isEmpty(password)) {
                    dialog.setTitle("Registering Now");
                    dialog.setMessage("please wait....");
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.show();

                    register(email,name,phone,location,password);
                }
            }
        });

    }

    private void register(final String email, final String name, final String phone, final String location, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                            String uid = currentUser.getUid();
                            // Write a message to the database
//                            database = FirebaseDatabase.getInstance();

                            mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Company").child(uid);
                            HashMap<String, String> userMap = new HashMap<>();
                            userMap.put("name", name);
                            userMap.put("phone", phone);
                            userMap.put("location", location);
                            userMap.put("email", email);


                            mDatabaseReference.setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        dialog.dismiss();
                                        Intent mainIntent = new Intent(CompanyRegistration.this, MainActivity.class);
                                        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(mainIntent);
                                        finish();
                                    }
                                }
                            });

                        } else {
                            // If sign in fails, display a message to the user.
                            dialog.hide();
                            Toast.makeText(CompanyRegistration.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
