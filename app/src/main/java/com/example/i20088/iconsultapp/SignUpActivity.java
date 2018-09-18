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

import com.example.i20088.iconsultapp.Delegates.PatientResponse;
import com.example.i20088.iconsultapp.Manager.PatientManager;
import com.example.i20088.iconsultapp.Model.Patient;
import com.example.i20088.iconsultapp.Network.NetworkManager;

public class SignUpActivity extends AppCompatActivity implements TextWatcher, PatientResponse{
    private static final String TAG = "SignUpActivity";
    private EditText fName,lName;
    private EditText emailField,passwordField,phoneNo,gpsurgeryField,gpsurgpnamegeryField,remarksField;
    private Button submitButton, cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        initializeUI();
    }
    private View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.submitButton:
                    System.out.println("Button Listener=================");
                    submitButtonTapped(view);
                    System.out.println("after Button Listener=================");
                    break;
                default:
                    break;
            }
        }
    };

    public void initializeUI()
    {
        fName = (EditText) findViewById(R.id.fName);
        lName = (EditText) findViewById(R.id.lName);
        emailField = (EditText) findViewById(R.id.emailField);
        passwordField = (EditText) findViewById(R.id.passwordField);
        phoneNo = (EditText) findViewById(R.id.phoneField);
        gpsurgeryField = (EditText) findViewById(R.id.gpsurgeryField);
        gpsurgpnamegeryField = (EditText) findViewById(R.id.gpsurgeryField);
        remarksField = (EditText) findViewById(R.id.remarkField);
        submitButton = (Button) findViewById(R.id.submitButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);

        enableSubmit(false);
        fName.addTextChangedListener(this);
        lName.addTextChangedListener(this);
        emailField.addTextChangedListener(this);
        passwordField.addTextChangedListener(this);
        phoneNo.addTextChangedListener(this);
        gpsurgeryField.addTextChangedListener(this);
        gpsurgpnamegeryField.addTextChangedListener(this);
        remarksField.addTextChangedListener(this);

        submitButton.setOnClickListener(buttonListener);
        submitButton.setEnabled(true);
    }
    private void submitButtonTapped(View view) {
        Log.d(TAG, "SignUp");

        if (!validate()) {
            onSignUpFailed();
            return;
        }

        submitButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignUpActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("SigningUp...");
        progressDialog.show();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignUpSuccess or onSignUpFailed
                        onSignUpSuccess();
                        // onSignUpFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }
    private void enableSubmit(boolean shouldEnable) {
        submitButton.setEnabled(shouldEnable);
        int color = shouldEnable ? R.color.colorTheme : R.color.colorDisabledButton;
        submitButton.setBackgroundResource(color);
    }
    public void onSignUpSuccess() {
        String firstname = fName.getText().toString().trim();
        String lastname = lName.getText().toString().trim();
        String email = emailField.getText().toString().trim();
        String password = passwordField.getText().toString().trim();
        String phone = phoneNo.getText().toString().trim();
        String gpsurgery = gpsurgeryField.getText().toString().trim();
        String gpsurgpnamegery = gpsurgpnamegeryField.getText().toString().trim();
        String remarks = remarksField.getText().toString().trim();
        submitButton.setEnabled(true);
        NetworkManager manager = new NetworkManager(this);
        manager.requetCreatePatient(firstname, lastname, email, password, phone, gpsurgery, gpsurgpnamegery, remarks);
        finish();
    }
    public void onSignUpFailed() {
        Toast.makeText(getBaseContext(), "SignUp failed, Enter Valid email or password", Toast.LENGTH_LONG).show();

        submitButton.setEnabled(true);
    }
    @Override
    public void didGetPatient(Patient patient) {
        Intent intent = new Intent(this, MainActivity.class);
        PatientManager.getInstance().setPatient(patient);
        startActivity(intent);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        boolean shouldEnable = !fName.getText().toString().isEmpty() && !lName.getText().toString().isEmpty() && !emailField.getText().toString().isEmpty() && !passwordField.getText().toString().isEmpty()
                && !phoneNo.getText().toString().isEmpty() && !gpsurgeryField.getText().toString().isEmpty() && !gpsurgpnamegeryField.getText().toString().isEmpty() && !remarksField.getText().toString().isEmpty();
        enableSubmit(shouldEnable);
    }

    @Override
    public void afterTextChanged(Editable s) {

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

        if (password.isEmpty() || password.length() < 4 || password.length() > 12) {
            passwordField.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            passwordField.setError(null);
        }
        return valid;
    }
}
