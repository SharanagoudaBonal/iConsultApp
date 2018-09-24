package com.example.i20088.iconsultapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.i20088.iconsultapp.Model.User;
import com.example.i20088.iconsultapp.Network.UserManager;


import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private TextView firstName = null;
    private TextView lastName = null;
    private TextView emailId = null;
    private TextView phnoeNo = null;
    private TextView gpName = null;
    private TextView gpSurgery = null;
    private ImageView userImage = null;
   private TextView textView;
    User user = UserManager.getInstance().getUser();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        firstName = (TextView) findViewById(R.id.fName);
        lastName = (TextView) findViewById(R.id.lName);
        emailId = (TextView) findViewById(R.id.emailField);
        phnoeNo = (TextView) findViewById(R.id.phoneNo);
        gpName = (TextView) findViewById(R.id.gpnameField);
        gpSurgery = (TextView) findViewById(R.id.gpsurgeryField);
        userProfile();
       // initializeUI();
       // getUserInformation();
    }
    private void  userProfile(){
        User user = UserManager.getInstance().getUser();
        this.user = user;

        String firstname = user.getFirstName();
        String lastname = user.getLastName();
        String email = user.getEmail();
        String phone = user.getPhone();
        String gpname = user.getGpname();
        String gpsurgery = user.getGpsurgery();

        TextView fNametext = firstName.findViewById(R.id.fName);
        TextView lNametext = lastName.findViewById(R.id.lName);
        TextView emailText = emailId.findViewById(R.id.emailField);
        TextView pText = phnoeNo.findViewById(R.id.phoneNo);
        TextView gpNameText = gpName.findViewById(R.id.gpnameField);
        TextView gpSurText = gpSurgery.findViewById(R.id.gpsurgeryField);

        fNametext.setText(firstname);
        lNametext.setText(lastname);
        emailText.setText(email);
        pText.setText(phone);
        gpNameText.setText(gpname);
        gpSurText.setText(gpsurgery);

    }
}
