package com.example.i20088.iconsultapp.Parsers;

import com.example.i20088.iconsultapp.Model.Hospital;

import org.json.JSONException;
import org.json.JSONObject;

public class HospitalParser {
    public static Hospital parse (JSONObject json){
        Hospital hospital = null;
        try{
            int gpsurgid = json.isNull("gpsurgid") ? null : json.getInt("gpsurgid");
            String gpsurgcode = json.isNull("gpsurgcode") ? null : json.getString("gpsurgcode");
            String gpsurgname = json.isNull("gpsurgname") ? null : json.getString("gpsurgname");

            hospital = new Hospital(gpsurgid,gpsurgcode,gpsurgname);
            hospital.setGpsurgid(gpsurgid);
            hospital.setGpsurgcode(gpsurgcode);
            hospital.setGpsurgname(gpsurgname);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return hospital;
    }
}
