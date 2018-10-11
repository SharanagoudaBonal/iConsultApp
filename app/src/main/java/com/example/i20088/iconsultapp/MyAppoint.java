package com.example.i20088.iconsultapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.i20088.iconsultapp.Delegates.AppointmentResponse;
import com.example.i20088.iconsultapp.Manager.UserManager;
import com.example.i20088.iconsultapp.Model.Appointment;
import com.example.i20088.iconsultapp.Model.Doctor;
import com.example.i20088.iconsultapp.Model.Schedule;
import com.example.i20088.iconsultapp.Model.User;
import com.example.i20088.iconsultapp.Network.NetworkManager;
import com.example.i20088.iconsultapp.Utils.DateUtils;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;

public class MyAppoint extends AppCompatActivity implements AdapterView.OnItemClickListener, AppointmentResponse {
    private static final String TAG =MyAppoint.class.getSimpleName() ;
    private ListView listView;
    private FloatingActionButton actionButton;
    private ArrayList<Object> objectsList = new ArrayList<>();
    private User user = null;
    private MyAppointListAdapter adapter = null;
    private static ArrayList<Appointment> appointments1=new ArrayList<Appointment>();
    private static ArrayList<String> docId=new ArrayList<>();
    private static ArrayList<String> date=new ArrayList<>();
    private static ArrayList<Schedule> schedules=new ArrayList<>();
    private  static String mGpid;
    private RecyclerView mRecyclerView;
   private MyAppointAdapter myAppointAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appoints);
        //listView = (ListView) findViewById(R.id.listView);
       // adapter = new MyAppointListAdapter(this,objectsList);
      //  listView.setAdapter(adapter);
      //  listView.setOnItemClickListener(this);
        // get the reference of RecyclerView
        mRecyclerView=findViewById(R.id.recycle_view);
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        System.out.println("MyAppointAdapter calling");
        myAppointAdapter=new MyAppointAdapter();
        System.out.println("MyAppointAdapter ended");
        mRecyclerView.setAdapter(myAppointAdapter);

        actionButton = (FloatingActionButton) findViewById(R.id.addAppointmentButton);
        actionButton.setOnClickListener(buttonListener);
        //initView();
        fetchAppointment();
       fetchDoctors();
    }
    private View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.addAppointmentButton:
                    addAppntButtonTapped(view);
                    break;
                default:
                    break;
            }
        }
    };
    private void addAppntButtonTapped(View view) {
        actionButton = (FloatingActionButton) findViewById(R.id.addAppointmentButton);
        Intent intent = new Intent(this, CreateAppointment.class);
        startActivity(intent);
    }
   /* private void initView() {
        mRecyclerView=findViewById(R.id.recycle_view);
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
       System.out.println("MyAppointAdapter calling");
        myAppointAdapter=new MyAppointAdapter();
        System.out.println("MyAppointAdapter ended");
        mRecyclerView.setAdapter(myAppointAdapter);
        //myAppointAdapter.notifyDataSetChanged();
    }*/

    private void fetchDoctors() {
        user = UserManager.getInstance().getUser();
        System.out.println("MyAppoint == "+user.getUserId());
        NetworkManager manager = new NetworkManager(this);
        manager.requestDoctor();
    }

    private void fetchAppointment () {
        user = UserManager.getInstance().getUser();
        System.out.println("MyAppoint == "+user.getUserId());
        Date currentDate = new Date();
        String year = DateUtils.getYear(currentDate);
        String month = DateUtils.getMonth(currentDate);
        String day = DateUtils.getDay(currentDate);
        String dateParamString = year + "-" + month + "-" + day;
        NetworkManager manager = new NetworkManager(this);
        manager.getAppointments(user.getUserId(), dateParamString);
    }

    @Override
    public void didGetAppointment(ArrayList<Appointment> appointments) {
        showAppointmentsList(appointments);
    }
    private void showAppointmentsList(ArrayList<Appointment> appointments) {
        objectsList.clear();
        if (appointments.size() == 0) {
            return;
        }
        if (appointments.size() > 0) {
            user = UserManager.getInstance().getUser();
            ArrayList<Appointment> appointmentArrayList = new ArrayList();
            for (int index = 0; index < appointments.size(); index++) {
                Appointment eachCase = appointments.get(index);
                Appointment schDate = new Appointment(eachCase.getPatientId(), eachCase.getSchdate());
                if(user.getUserId().equals(eachCase.getPatientId())){
                    appointmentArrayList.add(schDate);
                }
            }
        }
    }

   public static ArrayList<Appointment> checkAppointment(ArrayList<Appointment> appointments,String uid)
    {

        for(int i=0;i<appointments.size();i++) {
            String id1= String.valueOf(appointments.get(i).getPatientId());
            if (id1.equals(uid))
            {
                mGpid= String.valueOf(appointments.get(i).getGpId());
                appointments1.add(appointments.get(i));
                docId.add(String.valueOf(appointments.get(i).getGpId()));
                date.add(appointments.get(i).getSchdate());
                Log.i(TAG, "checkAppointment: "+appointments.get(i));
            }
        }
        return appointments1;
    }
    //static String[] ar;
   // static ArrayList<String> ar1;
   //static Schedule schedule=new Schedule();
    public static ArrayList<Doctor> checkDoctors(ArrayList<Doctor> doctors)
    {
        ArrayList<Doctor> doctors1=new ArrayList<Doctor>();
        for(int i=0;i<doctors.size();i++) {
            Log.i(TAG, "doctor id: "+doctors.get(i).getDocId());
            String id= String.valueOf(doctors.get(i).getDocId());
            Log.i(TAG, "appointment ID: "+appointments1.get(0).getGpId());
            String mid= String.valueOf(appointments1.get(1).getGpId());
           // if(!TextUtils.isEmpty(mGpid))
            for(int j=0;j<docId.size();j++) {
                Log.i(TAG, "checkDoctors: " + docId.get(j));
                if (id.equals(docId.get(j))) {

                    Schedule schedule=new Schedule();
                    schedule.name=doctors.get(i).getFirstname()+doctors.get(i).getLastname();
                    schedule.date=date.get(j);
                    Log.i(TAG, "checkDoctors: " + schedule.name);
                    Log.i(TAG, "checkDoctors: " + schedule.date);
                    schedules.add(schedule);
                    doctors1.add(doctors.get(i));
                    Log.i(TAG, "checkDoctors: " + doctors.get(i).getDocId());

                }

            }
        }
        System.out.println("Doctors list in my appointment"+doctors1);
        return doctors1;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, LandingActivity.class);
        Object object = objectsList.get(position);
        if (object instanceof Appointment) {
            intent.putExtra("AppointmentObject", (Appointment) object);
        }
        startActivity(intent);
    }



    private class MyAppointAdapter extends RecyclerView.Adapter {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_appoint,parent,false);
            System.out.println("--------------");
            MyViewHolder vh = new MyViewHolder(view);
            System.out.println("=========");
            return vh;
        }

        @Override
        public void onBindViewHolder( RecyclerView.ViewHolder holder, int position) {
            ((MyViewHolder)holder).bindTo(schedules.get(holder.getAdapterPosition()));
        }

        @Override
        public int getItemCount() {
            return schedules.size();
        }



        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView textName;
            public TextView textDate;
            public MyViewHolder(View itemView) {
                super(itemView);

                textName=itemView.findViewById(R.id.text_name);
                textDate=itemView.findViewById(R.id.text_date);
                System.out.println("ddddd nnnnn "+textName);
                System.out.println("ddddd nnnnn "+textDate);
            }

            public void bindTo(Schedule schedule) {
                textName.setText(schedule.name);
                textDate.setText(schedule.date);

                System.out.println("ddddd nnnnn "+schedule.name);
                System.out.println("ddddd nnnnn "+schedule.date);
            }
        }
    }
}
