package com.example.i20088.iconsultapp.Network;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetworkTask extends AsyncTask<String, Void, JSONObject> {
    private final int requestTypeIndex = 0;
    private final int httpMethodIndex = 1;
    private final int urlIndex = 2;
    private final int httpBodyIndex = 3;

    private Context context;
    private NetworkTaskResult callback;
    private boolean showProgress = false;
    private String requestTypeString = null;

    public NetworkTask(NetworkTaskResult callback) {
        this.callback = callback;
    }

    public NetworkTask() {

    }
    @Override
    protected void onPreExecute() {
    }

    @Override
    protected JSONObject doInBackground(String... params) {
        this.requestTypeString = params[requestTypeIndex];
        String urlString = params[urlIndex];
        String httpMethod = params[httpMethodIndex];
        String result = null;
        String inputLine = null;
        JSONObject jsonObject = null;
        System.out.println("urlString "+urlString);
        System.out.println("httpMethod "+httpMethod);
        HttpURLConnection connection = null;
        try {
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(httpMethod);
            connection.setRequestProperty("Content-Type", "application/json");
            String authValue = OAuthConstants.tokenType + " " + OAuthConstants.token;
            connection.setRequestProperty("Authorization", authValue);


            switch (httpMethod) {
                case "POST":
                    prepareOutputStream(connection, params[httpBodyIndex]);
                    break;
                case "PUT":
                    prepareOutputStream(connection, params[httpBodyIndex]);
                    break;
                default:
                    break;
            }
            connection.connect();

            int responseCode = connection.getResponseCode();
            System.out.println("RespCode==---------- "+responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) {

                InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
                BufferedReader reader = new BufferedReader(streamReader);
                StringBuilder stringBuilder = new StringBuilder();

                while((inputLine = reader.readLine()) != null){
                    stringBuilder.append(inputLine);
                }
                reader.close();
                streamReader.close();
                result = stringBuilder.toString();
                System.out.println("Result------ "+result);
                Object json = new JSONTokener(result).nextValue();
                if (json instanceof JSONArray) {
                    Log.d("NetworkTask", "Result = "+result);
                    System.out.println("Result JsonArray ===== "+result);
                    result = "{ data: " + result + "}";
                    jsonObject = new JSONObject(result);
                    Log.d("NetworkTask", "JsonArray result ==== "+result);
                }
                else if (json instanceof JSONObject) {
                    System.out.println("Result JsonObject ======  "+result);
                    Log.d("NetworkTask", "JSonObject result ==== "+result);
                    jsonObject = new JSONObject(result);
                }
            }
        } catch (MalformedURLException e) {
            Log.d("NetworkTask", "MalformedURLException");
            e.printStackTrace();
        } catch (IOException e) {
            Log.d("NetworkTask", "IOException");
            e.printStackTrace();
        } catch (JSONException e) {
            Log.d("NetworkTask", "JSONException");
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            if (jsonObject == null) {
                JSONObject object = new JSONObject();
                try {
                    object= object.put("data", result);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                jsonObject = object;
            }
        }
        return jsonObject;
    }
    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        RequestType requestType = RequestType.getRequestType(this.requestTypeString);
        System.out.println("NetworkTask--- RequestType "+requestType);
        System.out.println("NetworkTask--- JsonBody "+jsonObject);
        callback.networkResponse(requestType, jsonObject);
    }

    private void prepareOutputStream(HttpURLConnection connection, String bodyString) {
        try {
            OutputStreamWriter streamWriter = new OutputStreamWriter(connection.getOutputStream());
            BufferedWriter writer = new BufferedWriter(streamWriter);
            writer.write(bodyString);
            streamWriter.flush();
            writer.flush();
            streamWriter.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}