package com.example.i20088.iconsultapp.Manager;
import com.example.i20088.iconsultapp.Model.Appointment;

public class AppointmentManager {
    private static AppointmentManager instance = null;

    private AppointmentManager() {
    }

    private Appointment appointment = null;

    public static AppointmentManager getInstance() {
        if (instance == null) {
            instance = new AppointmentManager();
        }
        return instance;
    }
    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }
}
