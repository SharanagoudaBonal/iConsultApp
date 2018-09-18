package com.example.i20088.iconsultapp.Network;

import android.content.Context;
import android.content.Intent;

import android.util.Log;

import com.example.i20088.iconsultapp.Delegates.UserResponse;
import com.example.i20088.iconsultapp.Manager.PatientManager;
import com.example.i20088.iconsultapp.Model.Patient;
import com.example.i20088.iconsultapp.Model.User;
import com.example.i20088.iconsultapp.Parsers.PatientParser;
import com.example.i20088.iconsultapp.Parsers.UserParser;

import org.json.JSONException;
import org.json.JSONObject;

public class NetworkManager implements NetworkTaskResult {
    private static String baseUrl = null;
    private static String URL_PART_KEY = "urlpart";
    private static String HTTP_METHOD_KEY = "method";
    private static String HTTP_BODY_KEY = "body";

    private Context context = null;
    private boolean isLoginRequest = false;

    private String username = null;
    private String password = null;

    public NetworkManager(Context context) {
        this.context = context;
    }

    private String getBaseUrl() {
        if (baseUrl == null) {
            baseUrl = ApiLookupUtil.getBaseUrl(context);
        }
        return ApiLookupUtil.getBaseUrl(context);
    }

    public void requestLoginUser(String username, String password) {
        this.username = username;
        this.password = password;
        isLoginRequest = true;

        if (!isLoginRequest) {
            requestLoginUser(this.username, this.password);
            return;
        }
        JSONObject jsonObject = ApiLookupUtil.lookUpRequest(context, RequestType.login);
        try {
            String urlpart = jsonObject.getString(URL_PART_KEY);
            String httpMethod = jsonObject.getString(HTTP_METHOD_KEY);
            JSONObject bodyObject = jsonObject.getJSONObject(HTTP_BODY_KEY);
            bodyObject.put("username", username);
            bodyObject.put("password", password);

            String completeApiUrl = getBaseUrl() + urlpart;
            String bodyString = bodyObject.toString();

            new NetworkTask(this).execute(RequestType.login.name(), httpMethod, completeApiUrl, bodyString);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void requetCreatePatient(String firstname, String lastname,String email,String password,String phone,String gpsurgery,String gpsurgpnamegery,String remarks) {
        JSONObject jsonObject = ApiLookupUtil.lookUpRequest(context, RequestType.userCreation);
        try {
            System.out.println("Request create patient -------------- starated");
            String urlpart = jsonObject.getString(URL_PART_KEY);
            String httpMethod = jsonObject.getString(HTTP_METHOD_KEY);
            JSONObject object = jsonObject.getJSONObject(HTTP_BODY_KEY);
            object.put("firstname", firstname);
            object.put("lastname", lastname);
            object.put("email", email);
            object.put("password", password);
            object.put("phone", phone);
            object.put("gpsurgery", gpsurgery);
            object.put("gpsurgpnamegery", gpsurgpnamegery);
            object.put("remarks", remarks);

            String completApiUrl = getBaseUrl()+urlpart;
            String bodyString = object.toString();

            new NetworkTask(this).execute(RequestType.userCreation.name(), httpMethod, completApiUrl, bodyString);
            System.out.println("Request create patient -------------- ended");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void networkResponse(RequestType requestType, JSONObject jsonObject) {
        Log.d("ManagerTask", jsonObject.toString());
        switch (requestType) {
            case login:
                User user = UserParser.parse(jsonObject);
                if (user != null) {
                    ((UserResponse) context).didGetUser(user);
                }
                break;
            case userCreation:
                Patient createPatient = PatientParser.parse(jsonObject);
                PatientManager.getInstance().setPatient(createPatient);
                Intent patientIntent = new Intent();
                patientIntent.setAction("setPatientState");
                context.sendBroadcast(patientIntent);
                break;
            default:
                break;
        }
    }
}
