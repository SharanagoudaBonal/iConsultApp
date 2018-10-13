package com.example.i20088.iconsultapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.i20088.iconsultapp.Model.User;
import com.example.i20088.iconsultapp.Manager.UserManager;

public class MyProfile extends AppCompatActivity {
    private static final String TAG = "MyProfile";
    private TextView firstName = null;
    private TextView lastName = null;
    private TextView emailId = null;
    private TextView phnoeNo = null;
    private TextView gpName = null;
    private TextView gpSurgery = null;
    private ImageView userImage = null;
    private Button myAppointmentButton;
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
        initializeUI();

    }
    private View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.appointmentsButton:
                    myAppointmentButtonTapped(view);
                    break;
                    default:
                        break;
            }
        }
    };
    private void initializeUI() {
        myAppointmentButton = (Button) findViewById(R.id.appointmentsButton);
        myAppointmentButton.setOnClickListener(buttonListener);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_logout:
               // Toast.makeText(getApplicationContext(),"LogOut",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,LoginActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void  userProfile(){
        User user = UserManager.getInstance().getUser();
        this.user = user;

        TextView fNametext = firstName.findViewById(R.id.fName);
       // TextView lNametext = lastName.findViewById(R.id.lName);
        TextView emailText = emailId.findViewById(R.id.emailField);
        TextView pText = phnoeNo.findViewById(R.id.phoneNo);
        TextView gpNameText = gpName.findViewById(R.id.gpnameField);
        TextView gpSurText = gpSurgery.findViewById(R.id.gpsurgeryField);
if(user!=null) {
    String firstname = user.getFirstName();
    String lastname = user.getLastName();
    String email = user.getEmail();
    String phone = user.getPhone();
    String gpname = user.getGpname();
    String gpsurgery = user.getGpsurgery();
    fNametext.setText(firstname + " " + user.getLastName());
    // lNametext.setText(lastname);
    emailText.setText(email);
    pText.setText(phone);
    gpNameText.setText("Gp Name: " + gpname);
    gpSurText.setText("GP Surgery: " + gpsurgery);
}
    }
    private void myAppointmentButtonTapped(View view) {
        myAppointmentButton = (Button) findViewById(R.id.appointmentsButton);
        Intent intent = new Intent(this, MyAppoint.class);
        startActivity(intent);
    }
}
