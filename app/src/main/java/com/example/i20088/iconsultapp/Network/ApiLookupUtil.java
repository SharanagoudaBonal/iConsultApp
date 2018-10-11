package com.example.i20088.iconsultapp.Network;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ApiLookupUtil {
    public static final String baseUrlKey = "baseUrl";
    private static final String loginKey = "login";
    private static final String userCreationKey = "userCreation";
    private static final String allAppointmenstKey = "allAppointments";
    private static final String allDoctorsKey = "allDoctors";
    private static final String allPatientsKey = "allPatients";
    private static final String allHospitalsKey = "allHospitals";
    private static final String createAppointmentKey = "createAppointment";
    public static String getBaseUrl(Context context){
        String baseUrl=null;
        JSONObject requests = loadRequestsAsset(context);
       try {
           baseUrl = requests.getString(baseUrlKey);
        }catch (JSONException e){
           e.printStackTrace();
       }
       return baseUrl;
    }
    public static JSONObject lookUpRequest(Context context, RequestType requestType){
        JSONObject jsonTypeObject = null;
        try{
            JSONObject requests = loadRequestsAsset(context);
            switch (requestType){
                case login:
                    jsonTypeObject = getRequestJson(requests, loginKey);
                    break;
                case userCreation:
                    jsonTypeObject = getRequestJson(requests, userCreationKey);
                    break;
                case allAppointments:
                    jsonTypeObject = getRequestJson(requests, allAppointmenstKey);
                    break;
                case allDoctors:
                    jsonTypeObject = getRequestJson(requests, allDoctorsKey);
                    break;
                case allPatients:
                    jsonTypeObject = getRequestJson(requests, allPatientsKey);
                    break;
                case createAppointment:
                    jsonTypeObject = getRequestJson(requests, createAppointmentKey);
                    break;
                    default:
                        break;
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return jsonTypeObject;
    }
    public static JSONObject loadRequestsAsset(Context context) {
        String result = null;
        String inputLine = null;
        JSONObject jsonObject = null;

        try {
            InputStreamReader streamReader = new InputStreamReader(context.getAssets().open("requests.json"));

            BufferedReader reader = new BufferedReader(streamReader);
            StringBuilder stringBuilder = new StringBuilder();

            while((inputLine = reader.readLine()) != null){
                stringBuilder.append(inputLine);
            }
            reader.close();
            streamReader.close();

            result = stringBuilder.toString();
            //jsonObject=jsonObject.getJSONObject(result);
          jsonObject = new JSONObject(result);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    private static JSONObject getRequestJson(JSONObject jsonObject, String key) throws JSONException {
        JSONObject jsonObj = null;
        jsonObj = jsonObject.getJSONObject(key);
        return jsonObj;
    }
}
