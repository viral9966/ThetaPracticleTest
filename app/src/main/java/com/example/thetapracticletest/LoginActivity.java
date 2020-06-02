package com.example.thetapracticletest;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private Button buttonLogin;
    private EditText editTextEmail;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setUpView();

        setUpClickListener();
    }

    private void setUpView() {
        buttonLogin = findViewById(R.id.button_login);
        editTextEmail = findViewById(R.id.edittext_email);
        editTextPassword = findViewById(R.id.edittext_password);
    }

    private void setUpClickListener() {
        buttonLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_login:
                checkDataEntered();
                break;
        }
    }

    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    void checkDataEntered() {
        boolean isValidData = true;

        if (isEmpty(editTextEmail) == true) {
            isValidData = false;
            editTextEmail.setError("Email ID is required!");
        } else if (isEmail(editTextEmail) == false) {
            isValidData = false;
            editTextEmail.setError("Enter valid email!");
        } else if (isEmpty(editTextPassword) == true) {
            isValidData = false;
            editTextPassword.setError("Password is required!");
        } else if (editTextPassword.getText().toString().length() < 4) {
            isValidData = false;
            editTextPassword.setError("Enter valid password!");
        }

        if (isValidData) {
            if (editTextEmail.getText().toString().equalsIgnoreCase("test@123.com")
                    && editTextPassword.getText().toString().equalsIgnoreCase("123456")) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "Wrong Email ID or Password!", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
