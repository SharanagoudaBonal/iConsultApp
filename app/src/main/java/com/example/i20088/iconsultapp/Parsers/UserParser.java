package com.example.i20088.iconsultapp.Parsers;

import com.example.i20088.iconsultapp.Model.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UserParser {

    public static User parse(JSONObject json) {
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
    }
    /*JsonObject json = new JsonObject(response);

    JsonObject data = json.getJsonObject("data");

    String firstName = data.getString("firstName");
    String lastName = data.getString("lastName");*/
}
