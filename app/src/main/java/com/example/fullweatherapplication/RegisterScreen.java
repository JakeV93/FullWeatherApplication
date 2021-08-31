package com.example.fullweatherapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterScreen extends AppCompatActivity {

    private EditText email_reg, pass_reg;
    private Button reg_button;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_register);

        email_reg = findViewById(R.id.reg_email);
        pass_reg = findViewById(R.id.reg_pass);
        reg_button = findViewById(R.id.reg_button);

        reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = email_reg.getText().toString();
                String password = pass_reg.getText().toString();

                mAuth = FirebaseAuth.getInstance();

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(RegisterScreen.this, "Fill all fields", Toast.LENGTH_SHORT).show();
                }   else if (password.length() < 6 || password.length() > 14) {
                    Toast.makeText(RegisterScreen.this, "Password must be 6-14 characters", Toast.LENGTH_SHORT).show();
                }   else {
                    registerNewUser(email, password);
                }
            }
        });
    }

    private void registerNewUser(String email, String password) {

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegisterScreen.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(RegisterScreen.this, "Registration successful", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(RegisterScreen.this, MainActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(RegisterScreen.this, "Registration failed", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}