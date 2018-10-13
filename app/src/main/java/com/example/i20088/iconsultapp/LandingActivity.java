package com.example.i20088.iconsultapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class LandingActivity extends AppCompatActivity {
    private Button buttonSignUp;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        initializeUI();
    }

    private View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.buttonLogin:
                    loginButtonTapped(view);
                    break;
                case R.id.buttonSignUp:
                    signUpButtonTapped(view);
                    break;
                default:
                    break;
            }
        }
    };
    private void initializeUI() {
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(buttonListener);
        buttonSignUp = (Button) findViewById(R.id.buttonSignUp);
        buttonSignUp.setOnClickListener(buttonListener);
    }

    private void loginButtonTapped(View view) {
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

        private void signUpButtonTapped(View view) {
            buttonSignUp = (Button) findViewById(R.id.buttonSignUp);
            Intent intent = new Intent(this, SignUpActivity.class);
            startActivity(intent);
        }

}
