package com.example.i20088.iconsultapp.Delegates;

import com.example.i20088.iconsultapp.Model.Appointment;

import java.util.ArrayList;

public interface AppointmentResponse {
    void didGetAppointment(ArrayList<Appointment> appointment);
}
