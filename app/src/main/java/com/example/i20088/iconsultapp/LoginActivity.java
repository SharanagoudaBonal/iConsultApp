package com.example.i20088.iconsultapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.i20088.iconsultapp.Delegates.UserResponse;
import com.example.i20088.iconsultapp.Model.User;
import com.example.i20088.iconsultapp.Network.NetworkManager;
import com.example.i20088.iconsultapp.Network.UserManager;

public class LoginActivity extends AppCompatActivity implements TextWatcher, UserResponse {

    private static final String TAG = "LoginActivity";
    private EditText emailField;
    private EditText passwordField;
    private Button loginButton;

    private View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.loginButton:
                    logInTapped(view);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializeUI();
    }

    private void initializeUI() {
        emailField = (EditText) findViewById(R.id.emailField);
        passwordField = (EditText) findViewById(R.id.passwordField);
        loginButton = (Button) findViewById(R.id.loginButton);

        enablelogIn(false);
        loginButton.setOnClickListener(buttonListener);
        emailField.addTextChangedListener(this);
        passwordField.addTextChangedListener(this);
    }

    private void logInTapped(View view) {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Logging...");
        progressDialog.show();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);

    }

    private void enablelogIn(boolean shouldEnable) {
        loginButton.setEnabled(shouldEnable);
        int color = shouldEnable ? R.color.colorTheme : R.color.colorDisabledButton;
        loginButton.setBackgroundResource(color);
    }



    @Override
    public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
        boolean shouldEnable = !emailField.getText().toString().isEmpty() && !passwordField.getText().toString().isEmpty();
        enablelogIn(shouldEnable);
    }
    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void didGetUser(User user) {
        Intent intent = new Intent(this, MainActivity.class);
        UserManager.getInstance().setUser(user);
        startActivity(intent);
    }
    public void onLoginSuccess() {
        String email = emailField.getText().toString().trim();
        String password = passwordField.getText().toString().trim();
        loginButton.setEnabled(true);
        NetworkManager manager = new NetworkManager(this);
        manager.requestLoginUser(email, password);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        loginButton.setEnabled(true);
    }
    public boolean validate() {
        boolean valid = true;

        String email = emailField.getText().toString().trim();
        String password = passwordField.getText().toString().trim();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailField.setError("enter a valid email address");
            valid = false;
        } else {
            emailField.setError(null);
        }

        if (password.isEmpty() || password.length() < 6 || password.length() > 12) {
            passwordField.setError("between 6 and 10 alphanumeric characters");
            valid = false;
        } else {
            passwordField.setError(null);
        }
        return valid;
    }
}
