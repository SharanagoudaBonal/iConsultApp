package com.example.i20088.iconsultapp.Parsers;

import android.util.Log;

import com.example.i20088.iconsultapp.Manager.DoctorManager;
import com.example.i20088.iconsultapp.Model.Doctor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DoctorParser {
    public static ArrayList<Doctor> parse(JSONObject json) {
        ArrayList<Doctor> doctors = new ArrayList();
        try {
            JSONArray jsonArray = json.getJSONArray("data");
            for (int index = 0; index < jsonArray.length(); index++) {
                try {
                    if (!jsonArray.get(index).equals(null)) {
                        JSONObject jsonObject = jsonArray.getJSONObject(index);
                        Doctor eachDoctor = parseDoctor(jsonObject);
                        Log.i("parse", " "+eachDoctor.getFirstname()+eachDoctor.getLastname());
                        doctors.add(eachDoctor);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return doctors;
    }
    public static Doctor parseDoctor(JSONObject json) {
        Doctor doctor = new Doctor();
        try {
                Integer docId = json.isNull("userId") ? null : json.getInt("userId");
                String firstname = json.isNull("firstname") ? null : json.getString("firstname");
                String lastname = json.isNull("lastname") ? null : json.getString("lastname");
                doctor.setDocId(docId);
                doctor.setFirstname(firstname);
                doctor.setLastname(lastname);
            } catch (JSONException e) {
            e.printStackTrace();
        }
        return doctor;
    }
}
