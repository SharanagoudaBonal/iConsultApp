package com.example.i20088.iconsultapp.Network;

import org.json.JSONObject;

public interface NetworkTaskResult {
    void networkResponse(RequestType requestType, JSONObject jsonObject);
}

