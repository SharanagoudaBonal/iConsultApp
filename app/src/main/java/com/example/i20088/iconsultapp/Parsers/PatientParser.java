package com.example.i20088.iconsultapp.Parsers;

import com.example.i20088.iconsultapp.Model.Patient;

import org.json.JSONException;
import org.json.JSONObject;

public class PatientParser {
    public static Patient parse(JSONObject jsonObject){
        Patient patient = null;
        try{
            String firatName = jsonObject.isNull("firatName") ? null : jsonObject.getString("firatName");
            String lastName = jsonObject.isNull("lastName") ? null : jsonObject.getString("lastName");
            String email = jsonObject.isNull("email") ? null : jsonObject.getString("email");
            String password = jsonObject.isNull("password") ? null : jsonObject.getString("password");
            String phone = jsonObject.isNull("phone") ? null : jsonObject.getString("phone");
            String gpsurgery = jsonObject.isNull("gpsurgery") ? null : jsonObject.getString("gpsurgery");
            String gpsurgpnamegery = jsonObject.isNull("gpsurgpnamegery") ? null : jsonObject.getString("gpsurgpnamegery");
            String remarks = jsonObject.isNull("remarks") ? null : jsonObject.getString("remarks");

            patient.setFirstname(firatName);
            patient.setLastName(lastName);
            patient.setEmail(email);
            patient.setPassword(password);
            patient.setPhone(phone);
            patient.setGpsurgery(gpsurgery);
            patient.setGpsurgpnamegery(gpsurgpnamegery);
            patient.setRemarks(remarks);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return patient;
    }
}
