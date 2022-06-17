package com.example.filmera;

import com.example.filmera.Models.APIResponse;

public interface OnFetchDataListener {
    void onFetched(APIResponse response, String message);
    void onError(String message);
}
