package com.example.thetapracticletest;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.thetapracticletest.utils.AppConstants;
import com.example.thetapracticletest.utils.AppPref;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private Button buttonLogin;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private AppPref appPref;

    @Override
    protected void onStart() {
        super.onStart();
        appPref = new AppPref(this);
        if (appPref.getLoggedInUser().equalsIgnoreCase(AppConstants.GetLoggedInStatusValue)) {
            Intent intent = new Intent(LoginActivity.this,NavigationDrawerActivity.class);
            startActivity(intent);
            finish();
        }
    }

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
            editTextEmail.setError(getString(R.string.text_email_required));
        } else if (isEmail(editTextEmail) == false) {
            isValidData = false;
            editTextEmail.setError(getString(R.string.text_validation_email));
        } else if (isEmpty(editTextPassword) == true) {
            isValidData = false;
            editTextPassword.setError(getString(R.string.text_password_required));
        } else if (editTextPassword.getText().toString().length() < 4) {
            isValidData = false;
            editTextPassword.setError(getString(R.string.text_validation_password));
        }

        if (isValidData) {
            if (editTextEmail.getText().toString().equalsIgnoreCase(AppConstants.AuthUserKey)
                    && editTextPassword.getText().toString().equalsIgnoreCase(AppConstants.AuthUserPassValue)) {
                appPref.setLoggedInUser(AppConstants.GetLoggedInStatusValue);
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                AppConstants.ShowNewAlert(this,getString(R.string.text_wrong_email_password));
            }
        }

    }
}
