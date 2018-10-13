package com.example.i20088.iconsultapp.Network;

import android.content.Context;
import android.content.Intent;

import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import com.example.i20088.iconsultapp.Delegates.AppResponse;
import com.example.i20088.iconsultapp.Delegates.AppointmentResponse;
import com.example.i20088.iconsultapp.Delegates.DoctorResponse;
import com.example.i20088.iconsultapp.Delegates.UserResponse;
import com.example.i20088.iconsultapp.Manager.AppManager;
import com.example.i20088.iconsultapp.Manager.DoctorManager;
import com.example.i20088.iconsultapp.Manager.UserManager;
import com.example.i20088.iconsultapp.Model.App;
import com.example.i20088.iconsultapp.Model.Appointment;
import com.example.i20088.iconsultapp.Model.Doctor;
import com.example.i20088.iconsultapp.Model.User;
import com.example.i20088.iconsultapp.MyAppoint;
import com.example.i20088.iconsultapp.Parsers.AppParser;
import com.example.i20088.iconsultapp.Parsers.AppointmentParser;
import com.example.i20088.iconsultapp.Parsers.DoctorParser;
import com.example.i20088.iconsultapp.Parsers.UserParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NetworkManager implements NetworkTaskResult {
    private static String baseUrl = null;
    private static String URL_PART_KEY = "urlpart";
    private static String HTTP_METHOD_KEY = "method";
    private static String HTTP_BODY_KEY = "body";

    private Context context = null;
    private boolean isLoginRequest = false;

    private String username = null;
    private String password = null;
    private String mGpid;

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
        System.out.println("Nm========== reqLogin " +username + " pass " +password);
       /* isLoginRequest = true;

        if (!isLoginRequest) {
            requestLoginUser(this.username, this.password);
            return;
        }*/
        JSONObject jsonObject = ApiLookupUtil.lookUpRequest(context, RequestType.login);
        try {
            String urlpart = jsonObject.getString(URL_PART_KEY);
            String httpMethod = jsonObject.getString(HTTP_METHOD_KEY);
            JSONObject bodyObject = jsonObject.getJSONObject(HTTP_BODY_KEY);
            Log.d("NetworkManager","bodyObject"+bodyObject);
            bodyObject.put("username", username);
            bodyObject.put("password", password);
            System.out.println("NM ==== Inside ReqLogin "+username+" pass "+password);
            String completeApiUrl = getBaseUrl() + urlpart;
            String bodyString = bodyObject.toString();

            System.out.println("ApiUrl "+completeApiUrl);
            System.out.println("bodyString "+bodyString);

            new NetworkTask(this).execute(RequestType.login.name(), httpMethod, completeApiUrl, bodyString);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void requetCreateUser(String firstname, String lastname,String email,String password,String phone,String gpname,String gpsurgery,String remarks) {
        JSONObject jsonObject = ApiLookupUtil.lookUpRequest(context, RequestType.userCreation);
        try {
            System.out.println("Request create patient -------------- started");
            String urlpart = jsonObject.getString(URL_PART_KEY);
            String httpMethod = jsonObject.getString(HTTP_METHOD_KEY);
            JSONObject object = jsonObject.getJSONObject(HTTP_BODY_KEY);
            object.put("firstname", firstname);
            object.put("lastname", lastname);
            object.put("email", email);
            object.put("password", password);
            object.put("phone", phone);
            object.put("gpsurgery", gpsurgery);
            object.put("gpname", gpname);
            object.put("remarks", remarks);

            System.out.println("Request user ----- "+username+" gpName "+gpname);
            String completApiUrl = getBaseUrl()+urlpart;
            String bodyString = object.toString();

            System.out.println("Request user ----- "+bodyString);
            new NetworkTask(this).execute(RequestType.userCreation.name(), httpMethod, completApiUrl, bodyString);
            System.out.println("Request create patient -------------- ended");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void createAppointment(String patientId, String gpId, String roomname, String schdate){
        JSONObject jsonObject = ApiLookupUtil.lookUpRequest(context, RequestType.createAppointment);
        try
        {
            String urlpart = jsonObject.getString(URL_PART_KEY);
            String httpMethod = jsonObject.getString(HTTP_METHOD_KEY);
            JSONObject object = jsonObject.getJSONObject(HTTP_BODY_KEY);
            object.put("patientId", patientId);
            object.put("gpId", gpId);
            object.put("roomname", roomname);
            object.put("schdate", schdate);
           // object.put("status", status);

            String completApiUrl = getBaseUrl()+urlpart;
            String bodyString = object.toString();

            System.out.println("Request create appointment ----- "+bodyString);
            new NetworkTask(this).execute(RequestType.createAppointment.name(), httpMethod, completApiUrl, bodyString);
            System.out.println("Request create appointment -------------- ended");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void fetchHospitals()
    {
        JSONObject jsonObject =ApiLookupUtil.lookUpRequest(context,RequestType.allHospitals);
        try {
            String urlpart = jsonObject.getString(URL_PART_KEY);
            String httpMethod = jsonObject.getString(HTTP_METHOD_KEY);

            String completeApiUrl = getBaseUrl()+urlpart;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void getAppointments(String userId, String dateString)
    {
        JSONObject jsonObject =ApiLookupUtil.lookUpRequest(context,RequestType.allAppointments);
        Log.d("GetAppointments ","JssonObject "+jsonObject);
        System.out.println("All appointments req === "+jsonObject);
        try {
            String urlpart = jsonObject.getString(URL_PART_KEY);
            String httpMethod = jsonObject.getString(HTTP_METHOD_KEY);

            String completeApiUrl = getBaseUrl()+urlpart;
            new NetworkTask(this).execute(RequestType.allAppointments.name(), httpMethod, completeApiUrl, null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void requestDoctor(){
        JSONObject jsonObject =ApiLookupUtil.lookUpRequest(context,RequestType.allDoctors);
        try {
            String urlpart = jsonObject.getString(URL_PART_KEY);
            String httpMethod = jsonObject.getString(HTTP_METHOD_KEY);

            String completeApiUrl = getBaseUrl()+urlpart;
            new NetworkTask(this).execute(RequestType.allDoctors.name(), httpMethod, completeApiUrl, null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void networkResponse(RequestType requestType, JSONObject jsonObject) {
        ArrayList<Doctor> doctors = new ArrayList<>();
        ArrayList<Appointment> appointments=new ArrayList<>();
        Log.d("NetworkManager === ","jsonobject"+ jsonObject.toString());
        Log.d("NetworkManager ===","requesttype"+ requestType.toString());
        switch (requestType) {
            case login:
                User user = UserParser.parse(jsonObject);
                if (user != null) {
                    ((UserResponse) context).didGetUser(user);
                }
                break;
            case userCreation:
               User createUser = UserParser.parse(jsonObject);
               if(createUser!=null) {
                   ((UserResponse) context).didGetUser(createUser);
                   UserManager.getInstance().setUser(createUser);
                   Intent userIntent = new Intent();
                   userIntent.setAction("setUserState");
                   context.sendBroadcast(userIntent);
               }
                break;
            case allAppointments:
                User user1= UserManager.getInstance().getUser();
                String id=user1.getUserId();
                appointments= MyAppoint.checkAppointment(AppointmentParser.parse(jsonObject),id);
                if(appointments.size()>0)
                mGpid= String.valueOf(appointments.get(0).getGpId());
                Log.i("allAppointments", "networkResponse: "+mGpid);
                Log.i("", "Size "+appointments.size());
                ((AppointmentResponse)context).didGetAppointment(appointments);
                break;
            case allHospitals:
                break;
            case allPatients:
                break;
            case allDoctors:
                   doctors= MyAppoint.checkDoctors(DoctorParser.parse(jsonObject));
                Log.i("allDoctors", "Size "+doctors.size());
               // ((DoctorResponse)context).didGetDoctor(doctors);
                break;
            case createAppointment:
                App createApp = AppParser.parse(jsonObject);
                if(createApp!=null) {
                    ((AppResponse) context).didGetApp(createApp);
                    AppManager.getInstance().setApp(createApp);
                    Intent appIntent = new Intent();
                    appIntent.setAction("setAppState");
                    context.sendBroadcast(appIntent);
                }
                break;
            default:
                break;
        }
    }
}