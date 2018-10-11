package com.example.i20088.iconsultapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.i20088.iconsultapp.Delegates.DoctorResponse;
import com.example.i20088.iconsultapp.Delegates.UserResponse;
import com.example.i20088.iconsultapp.Manager.DoctorManager;
import com.example.i20088.iconsultapp.Model.Doctor;
import com.example.i20088.iconsultapp.Model.User;
import com.example.i20088.iconsultapp.Network.NetworkManager;
import com.example.i20088.iconsultapp.Manager.UserManager;

import java.util.ArrayList;

public class SignUpActivity extends AppCompatActivity implements TextWatcher, UserResponse {
    private static final String TAG = "SignUpActivity";
    private EditText fName, lName;
    private EditText emailField, passwordField, phoneNo, gpNameField, gpsurgeryField, remarksField;
    private Button submitButton, cancelButton, checkBox;
    private ListView listView;
    private Doctor doctor;
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

    public void initializeUI() {
        fName = (EditText) findViewById(R.id.fName);
        lName = (EditText) findViewById(R.id.lName);
        emailField = (EditText) findViewById(R.id.emailField);
        passwordField = (EditText) findViewById(R.id.passwordField);
        phoneNo = (EditText) findViewById(R.id.phoneField);
        gpNameField = (EditText) findViewById(R.id.gpNameField);
        gpsurgeryField = (EditText) findViewById(R.id.gpsurgeryField);
        remarksField = (EditText) findViewById(R.id.remarkField);
        submitButton = (Button) findViewById(R.id.submitButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);
        checkBox = (CheckBox) findViewById(R.id.checkBoxField);

        enableSubmit(false);
        checkBox.setEnabled(false);
        fName.addTextChangedListener(this);
        lName.addTextChangedListener(this);
        emailField.addTextChangedListener(this);
        passwordField.addTextChangedListener(this);
        phoneNo.addTextChangedListener(this);
        gpNameField.addTextChangedListener(this);
        gpsurgeryField.addTextChangedListener(this);
        remarksField.addTextChangedListener(this);
        checkBox.addTextChangedListener(this);
        submitButton.setOnClickListener(buttonListener);
        checkBox.setEnabled(true);
        submitButton.setEnabled(true);
    }

    private void submitButtonTapped(View view) {

        submitButton.setEnabled(false);
        checkBox.setEnabled(false);
        String firstname = fName.getText().toString().trim();
        String lastname = lName.getText().toString().trim();
        String email = emailField.getText().toString().trim();
        String password = passwordField.getText().toString().trim();
        String phone = phoneNo.getText().toString().trim();
        String gpname = gpNameField.getText().toString().trim();
        String gpsurgery = gpsurgeryField.getText().toString().trim();
        String remarks = remarksField.getText().toString().trim();
        submitButton.setEnabled(true);
        checkBox.setEnabled(true);
        NetworkManager manager = new NetworkManager(this);
        manager.requetCreateUser(firstname, lastname, email, password, phone, gpname, gpsurgery, remarks);
        finish();
    }

    private void enableSubmit(boolean shouldEnable) {
        submitButton.setEnabled(shouldEnable);
        int color = shouldEnable ? R.color.colorTheme : R.color.colorDisabledButton;
        submitButton.setBackgroundResource(color);
    }

    @Override
    public void didGetUser(User user) {
        boolean isUser = UserManager.getInstance().isUser();
        if (isUser) {
            UserManager.getInstance().createUser(this);
            Intent intent = new Intent(this, LandingActivity.class);
            UserManager.getInstance().setUser(user);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "STRING MESSAGE", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        boolean shouldEnable = !fName.getText().toString().isEmpty() && !lName.getText().toString().isEmpty() && !emailField.getText().toString().isEmpty() && !passwordField.getText().toString().isEmpty()
                && !phoneNo.getText().toString().isEmpty() && !gpNameField.getText().toString().isEmpty() && !gpsurgeryField.getText().toString().isEmpty() && !remarksField.getText().toString().isEmpty()
        && !fName.getText().toString().isEmpty();
        enableSubmit(shouldEnable);
    }

    @Override
    public void afterTextChanged(Editable s) {
    }

    private void fetchHospital(){

    }
    private void fetchDoctor(){

       String doctorName;
    }


}
