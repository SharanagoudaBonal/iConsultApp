package com.example.i20088.iconsultapp.Parsers;

import android.util.Log;

import com.example.i20088.iconsultapp.Manager.UserManager;
import com.example.i20088.iconsultapp.Model.Appointment;
import com.example.i20088.iconsultapp.Model.Doctor;
import com.example.i20088.iconsultapp.Model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.logging.LoggingMXBean;

public class AppointmentParser {

    public static ArrayList<Appointment> parse(JSONObject json) {
        ArrayList<Appointment> appointments = new ArrayList();
        User user= UserManager.getInstance().getUser();
        String id=user.getUserId();
        try {
            JSONArray jsonArray = json.getJSONArray("data");
            for (int index = 0; index < jsonArray.length(); index++) {
                try {
                    if (!jsonArray.get(index).equals(null)) {
                        JSONObject jsonObject = jsonArray.getJSONObject(index);
                        Appointment eachAppointment = parseAppointment(jsonObject);

                        Log.i("AppointmentParser", "id "+user.getUserId());
                        Log.i("AppointmentParser", "id "+eachAppointment.getPatientId());
                        Log.i("AppointmentParser", ""+id);
                        String id1= String.valueOf(eachAppointment.getPatientId());
                       // if(id1.equals(id));
                      //  {
                            Log.i("eachAppointment", "id "+ id +" "+id1);
                            appointments.add(eachAppointment);
                      //  }
                    //    Log.i("appointments", ""+appointments.get(0));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return appointments;

    }


     static Appointment parseAppointment(JSONObject jsonObject)
    {

        Appointment appointment = null;
        try{
            //String appointmentId = jsonObject.isNull("appointmentId") ? null : jsonObject.getString("appointmentId");
            String schdate = jsonObject.isNull("schdate") ? null : jsonObject.getString("schdate");
            String created_at = jsonObject.isNull("created_at") ? null : jsonObject.getString("created_at");
            String _id = jsonObject.isNull("_id") ? null : jsonObject.getString("_id");
            Integer patientId = jsonObject.isNull("patientId") ? null : jsonObject.getInt("patientId");
            Integer gpId = jsonObject.isNull("gpId") ? null : jsonObject.getInt("gpId");
            String roomname = jsonObject.isNull("roomname") ? null : jsonObject.getString("roomname");
            String status = jsonObject.isNull("status") ? null : jsonObject.getString("status");
            Integer scheduleId = jsonObject.isNull("scheduleId") ? null : jsonObject.getInt("scheduleId");
            Integer __v = jsonObject.isNull("__v") ? null : jsonObject.getInt("__v");

            appointment = new Appointment();
            //appointment.setAppointmentId(appointmentId);
            appointment.setSchdate(schdate);
            appointment.setCreated_at(created_at);
            appointment.set_id(_id);
            appointment.setPatientId(patientId);
            appointment.setGpId(gpId);
            appointment.setRoomname(roomname);
            appointment.setStatus(status);
            appointment.setScheduleId(scheduleId);
            appointment.set__v(__v);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("appointment parser == "+jsonObject);
        return appointment;
    }
}
