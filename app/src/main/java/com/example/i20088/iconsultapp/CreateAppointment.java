package com.example.i20088.iconsultapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.i20088.iconsultapp.Delegates.AppResponse;
import com.example.i20088.iconsultapp.Manager.AppManager;
import com.example.i20088.iconsultapp.Manager.DoctorManager;
import com.example.i20088.iconsultapp.Manager.UserManager;
import com.example.i20088.iconsultapp.Model.App;
import com.example.i20088.iconsultapp.Model.Appointment;
import com.example.i20088.iconsultapp.Model.Doctor;
import com.example.i20088.iconsultapp.Model.Schedule;
import com.example.i20088.iconsultapp.Model.User;
import com.example.i20088.iconsultapp.Network.NetworkManager;
import com.example.i20088.iconsultapp.Parsers.DoctorParser;
import com.example.i20088.iconsultapp.Utils.RandomString;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

public class CreateAppointment extends AppCompatActivity implements TextWatcher, View.OnClickListener, AppResponse, AdapterView.OnItemSelectedListener {
    private static final String TAG = "CreateAppointment";
   // private EditText gpName;
    Spinner spin;
    private Button subButton, canslButton;
    private Button btnDatePicker, btnTimePicker;
    private  EditText txtDate, txtTime;
    private int mYear, mMonth, mDay, mHour, mMinute;
    String[] gpName={"--Select GpName--","Dr. Surekha MuniRaj","Dr. John D Souza","Dr. Dhillon D Souza","Dr. Steve Jobs"};
   String[] gp={"1","3","4","5","14"};
    User user = UserManager.getInstance().getUser();
    Doctor doctor = DoctorManager.getInstance().getDoctor();
    private static ArrayList<Appointment> appointments1=new ArrayList<Appointment>();
    private static ArrayList<String> docId=new ArrayList<>();
    private static ArrayList<String> date=new ArrayList<>();
    private static ArrayList<Schedule> schedules=new ArrayList<>();
    private  static String mGpid;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_appointment);
       spin = (Spinner) findViewById(R.id.simpleSpinner);
       spin.setOnItemSelectedListener(this);

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,gpName);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);
        initializeUI();

    }
    private View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.submitButton:
                    submitButtonTapped(view);
                    break;
                default:
                    break;
            }
        }
    };

    public void initializeUI(){

        txtDate=(EditText)findViewById(R.id.date);
        txtTime=(EditText)findViewById(R.id.time);
        //spin = (Spinner) findViewById(R.id.simpleSpinner);
       // spin.setOnItemSelectedListener(this);
       // gpName = (EditText)findViewById(R.id.gpNameText);
        btnDatePicker=(Button)findViewById(R.id.btn_date);
        btnTimePicker=(Button)findViewById(R.id.btn_time);
        subButton = (Button)findViewById(R.id.submitButton);
        canslButton = (Button)findViewById(R.id.submitButton) ;

        enableSubmit(false);

        txtDate.addTextChangedListener(this);
        txtTime.addTextChangedListener(this);
       // spin.addTextChangedListener(this);
        subButton.setOnClickListener(buttonListener);
        canslButton.setOnClickListener(buttonListener);
        subButton.setEnabled(true);

        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);

    }
    //String patientId;
    //String gpId1;
    private void submitButtonTapped(View view) {
        subButton.setEnabled(false);
        String patientId = user.getUserId();
        TextView textView = (TextView)spin.getSelectedView();

        String gpId = textView.getText().toString().trim();
        //String gpId = doctorId()+"";
        String roomname = RandomString.randomString(10);
        System.out.println("gpId "+gpId +" pat  "+patientId);
        System.out.println("dateeeeeeeeeeeee "+txtDate.getText());
        System.out.println("dateeeeeeeeeeeee "+txtTime.getText());
        String schdate = ""+txtDate.getText().toString().trim()+"T"+txtTime.getText()+"".toString().trim();
        subButton.setEnabled(true);
        NetworkManager nm = new NetworkManager(this);
        //nm.createAppointment(patientId, gpId, roomname, schdate);

       // subButton = (Button) findViewById(R.id.submitButton);
       // Intent intent = new Intent(this, CreateAppointment.class);
        //startActivity(intent);
        finish();
    }
   // int dId;
    //JSONObject jsonObject;
   /* public int doctorId()
    {

        ArrayList<Doctor> doctors=new ArrayList<Doctor>();
        ArrayList<Doctor> doctors1=new ArrayList<Doctor>();
        MyAppoint.checkDoctors(doctors);
        System.out.println("Gp size "+MyAppoint.checkDoctors(doctors).size());
        for(int i=0;i<MyAppoint.checkDoctors(doctors).size();i++) {
            Schedule schedule=new Schedule();
            schedule.name=doctors.get(i).getFirstname()+doctors.get(i).getLastname();
            System.out.println("Gp name "+gpId+" "+schedule.name);
            if (gpId1.equals(schedule.name)) {

                dId=doctors.get(i).getDocId();
            }
            }
        System.out.println("Doctor id  "+dId);
        return dId;
    }*/
    private void enableSubmit(boolean shouldEnable) {
        subButton.setEnabled(shouldEnable);
        int color = shouldEnable ? R.color.colorTheme : R.color.colorDisabledButton;
        subButton.setBackgroundResource(color);
    }
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        boolean shouldEnable = !txtDate.getText().toString().isEmpty() && !txtTime.getText().toString().isEmpty();
        enableSubmit(shouldEnable);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
    @Override
    public void didGetApp(App app) {
        boolean isUser = AppManager.getInstance().isApp();
        if (isUser) {
            AppManager.getInstance().createAppointment(this);
            Intent intent = new Intent(this, AppAdapter.class);
            AppManager.getInstance().setApp(app);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "STRING MESSAGE", Toast.LENGTH_SHORT).show();
        }
    }
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position,long id) {
        Toast.makeText(getApplicationContext(), gpName[position], Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
    @Override
    public void onClick(View v) {
        if (v == btnDatePicker) {
            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                           // txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            txtDate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == btnTimePicker) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                           // txtTime.setText(hourOfDay + ":" + minute);
                            txtTime.setText(hourOfDay + ":" + minute +":00.000Z");
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    }
}
