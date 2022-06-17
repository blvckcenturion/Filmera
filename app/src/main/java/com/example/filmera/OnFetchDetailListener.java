package com.example.filmera;

import com.example.filmera.Models.DetailsResponse;

public interface OnFetchDetailListener {
    void onFetched(DetailsResponse response, String message);
    void onError(String message);
}

