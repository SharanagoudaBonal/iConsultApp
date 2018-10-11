package com.example.i20088.iconsultapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.example.i20088.iconsultapp.Model.Appointment;
import com.example.i20088.iconsultapp.Model.User;

import java.util.Date;


public class PastAppointmentFragment extends Fragment {
    private Appointment appointment = null;
    private User user = null;
    public PastAppointmentFragment() {
    }


    public void getAppointmentDate(){
        Date currentDate = new Date();

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_past_appointment, container, false);
    }
}