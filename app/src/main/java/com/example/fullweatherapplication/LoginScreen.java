package com.example.fullweatherapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginScreen extends AppCompatActivity {

    private Button login, register;
    private FirebaseAuth mAuth;
    private EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_login);

        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
        email = findViewById(R.id.EmailAddress);
        password = findViewById(R.id.Password);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginScreen.this, RegisterScreen.class);
                startActivity(i);
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_input = email.getText().toString();
                String password_input = password.getText().toString();

                mAuth = FirebaseAuth.getInstance();

                if (TextUtils.isEmpty(email_input) || TextUtils.isEmpty(password_input)) {
                    Toast.makeText(LoginScreen.this, "Fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    loginUser(email_input, password_input);
                }
            }
        });

    }

    private void loginUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Intent i = new Intent(LoginScreen.this, FrontPage.class);
                startActivity(i);
                finish();
            }
        });
    }
}