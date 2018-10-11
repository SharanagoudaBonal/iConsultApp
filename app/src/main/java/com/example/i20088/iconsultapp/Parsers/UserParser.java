package com.example.i20088.iconsultapp.Parsers;

import com.example.i20088.iconsultapp.Model.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UserParser {

    public static User parse(JSONObject json) {
        User user = null;
        try {
                String userId = json.isNull("userId") ? null : json.getString("userId");
                String firstName = json.isNull("firstname") ? "" : json.getString("firstname");
                String lastName = json.isNull("lastname") ? "" : json.getString("lastname");
                String email = json.isNull("email") ? "" : json.getString("email");
                String phone = json.isNull("phone") ? "" : json.getString("phone");
                String status = json.isNull("status") ? "" : json.getString("status");
                String gpname = json.isNull("gpname") ? "" : json.getString("gpname");
                String gpsurgery = json.isNull("gpsurgery") ? "" : json.getString("gpsurgery");
                //  String userType = jsonObject.isNull("userType") ? "" : jsonObject.getString("userType");
                String remarks = json.isNull("remarks") ? "" : json.getString("remarks");
              // boolean isPatient = true;
          //  Boolean userType = type.get('userType').equals("1") ? true : false;
                user= new User();
               // user = new User(userId,firstName,lastName,email,phone,status,gpname,gpsurgery,remarks);
                user.setUserId(userId);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setEmail(email);
                user.setPhone(phone);
                user.setStatus(status);
                user.setGpname(gpname);
                user.setGpsurgery(gpsurgery);
                user.setRemarks(remarks);
            } catch (JSONException e1) {
            e1.printStackTrace();
        }
        System.out.println("User parser == "+user);
        return user;
    }

    /*public static User parse(JSONObject json) {
        User user = null;
        try {
            JSONArray jsonArray = json.getJSONArray("data");
            if (jsonArray.length() == 1) {
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                String userId = jsonObject.isNull("userId") ? null : jsonObject.getString("userId");
                String firstName = jsonObject.isNull("firstname") ? "" : jsonObject.getString("firstname");
                String lastName = jsonObject.isNull("lastname") ? "" : jsonObject.getString("lastname");
                String email = jsonObject.isNull("email") ? "" : jsonObject.getString("email");
                String phone = jsonObject.isNull("phone") ? "" : jsonObject.getString("phone");
                String status = jsonObject.isNull("status") ? "" : jsonObject.getString("status");
                String gpsurgery = jsonObject.isNull("gpsurgery") ? "" : jsonObject.getString("gpsurgery");
                String gpsurgpnamegery = jsonObject.isNull("gpsurgpnamegery") ? "" : jsonObject.getString("gpsurgpnamegery");
                //  String userType = jsonObject.isNull("userType") ? "" : jsonObject.getString("userType");
                String remarks = jsonObject.isNull("remarks") ? "" : jsonObject.getString("remarks");

                user = new User();
                user.setUserId(userId);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setEmail(email);
                user.setPhone(phone);
                user.setStatus(status);
                user.setGpsurgery(gpsurgery);
                user.setGpsurgpnamegery(gpsurgpnamegery);
                user.setRemarks(remarks);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;
    }*/
    /*JsonObject json = new JsonObject(response);

    JsonObject data = json.getJsonObject("data");

    String firstName = data.getString("firstName");
    String lastName = data.getString("lastName");*/
}
