package com.example.i20088.iconsultapp.Network;

import android.content.Context;
import android.content.Intent;

import android.util.Log;

import com.example.i20088.iconsultapp.Delegates.UserResponse;
import com.example.i20088.iconsultapp.Model.User;
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
    public void networkResponse(RequestType requestType, JSONObject jsonObject) {
        Log.d("ManagerTask", jsonObject.toString());
        switch (requestType) {
            case login:
                User user = UserParser.parse(jsonObject);
                if (user != null) {
                    ((UserResponse) context).didGetUser(user);
                }
                break;
            default:
                break;
        }
    }
}