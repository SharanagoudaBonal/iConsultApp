package com.example.i20088.iconsultapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.i20088.iconsultapp.Delegates.DoctorResponse;
import com.example.i20088.iconsultapp.Model.Appointment;
import com.example.i20088.iconsultapp.Model.Doctor;
import com.example.i20088.iconsultapp.Model.User;
import com.example.i20088.iconsultapp.Network.NetworkManager;
import com.example.i20088.iconsultapp.Utils.DateUtils;

import java.util.ArrayList;
import java.util.Date;

public class MyAppointments extends AppCompatActivity implements DoctorResponse {
    private ListView listView;
    private TextView noDoctorsTextView;
    private ArrayList<Object> objectsList = new ArrayList<>();
    private PastAppointmentFragment adapter = null;
    private User user;
    private Appointment appointment;
    private FloatingActionButton addAppointmentButton;
    FrameLayout simpleFrameLayout;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        simpleFrameLayout = (FrameLayout) findViewById(R.id.simpleFrameLayout);
        tabLayout = (TabLayout) findViewById(R.id.simpleTabLayout);
        listView = findViewById(R.id.listview);
        TabLayout.Tab firstTab = tabLayout.newTab();
        firstTab.setText("Past Appointment");
        firstTab.setIcon(R.drawable.ic_launcher);

        tabLayout.addTab(firstTab);
        TabLayout.Tab secondTab = tabLayout.newTab();
        secondTab.setText("Future Appointment");
        secondTab.setIcon(R.drawable.ic_launcher);
        tabLayout.addTab(secondTab);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Adapter adapter = null;
                Fragment fragment = null;
                switch (tab.getPosition()) {
                    case 0:
                        fragment = new PastAppointmentFragment();
                       // fetchAppontment();
                        break;
                    case 1:
                        fragment = new FutureAppointmentFragment();
                       // fetchAppontment();
                        break;
                }
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.simpleFrameLayout, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
               // initializeUI();
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

   /* private View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.addAppointmentButton:
                    addAppointmentButtonTapped(view);
                    break;
                default:
                    break;
            }
        }
    };

    private void initializeUI() {
        addAppointmentButton = (FloatingActionButton) findViewById(R.id.addAppointmentButton);
        addAppointmentButton.setOnClickListener(buttonListener);
    }

    private void addAppointmentButtonTapped(View view) {
        addAppointmentButton = (FloatingActionButton) findViewById(R.id.addAppointmentButton);
        Intent intent = new Intent(this, LandingActivity.class);
        startActivity(intent);
    }
*/
    @Override
    public void didGetDoctor(ArrayList<Doctor> doctors) {
        showDoctorsList(doctors);
    }
    private void showDoctorsList(ArrayList<Doctor> doctors) {
        objectsList.clear();
        if (doctors.size() == 0) {
            // adapter.notifyDataSetChanged();
            listView.setVisibility(View.INVISIBLE);
            noDoctorsTextView.setVisibility(View.VISIBLE);
            return;
        }
    }
    private void fetchAppontment () {
        Date currentDate = new Date();
        String year = DateUtils.getYear(currentDate);
        String month = DateUtils.getMonth(currentDate);
        String day = DateUtils.getDay(currentDate);
        String dateParamString = year + "-" + month + "-" + day;
        NetworkManager manager = new NetworkManager(this);
        manager.getAppointments(this.user.getUserId(), dateParamString);
    }
}