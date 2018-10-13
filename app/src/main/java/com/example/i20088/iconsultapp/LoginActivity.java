package com.example.i20088.iconsultapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.i20088.iconsultapp.Delegates.UserResponse;
import com.example.i20088.iconsultapp.Model.User;
import com.example.i20088.iconsultapp.Network.NetworkManager;
import com.example.i20088.iconsultapp.Manager.UserManager;

public class LoginActivity extends AppCompatActivity implements TextWatcher, UserResponse {

    private static final String TAG = "LoginActivity";
    private EditText emailField;
    private EditText passwordField;
    private Button loginButton;
    private Button cancelButton;

    private View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.loginButton:
                    logInTapped(view);
                    break;
                case R.id.cancelButton:
                    cancelTapped(view);
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
        cancelButton = (Button) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(buttonListener);

        enablelogIn(false);
        loginButton.setOnClickListener(buttonListener);
        emailField.addTextChangedListener(this);
        passwordField.addTextChangedListener(this);
        loginButton.setEnabled(true);
    }

    private void logInTapped(View view) {

        String email = emailField.getText().toString().trim();
        String password = passwordField.getText().toString().trim();
        NetworkManager manager = new NetworkManager(this);
        manager.requestLoginUser(email, password);
    }
    private void cancelTapped(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
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
        Intent intent = new Intent(this, MyProfile.class);
        UserManager.getInstance().setUser(user);
        startActivity(intent);
    }


    /*public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        loginButton.setEnabled(false);
    */
    /*public boolean validate() {
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
    }*/
}
