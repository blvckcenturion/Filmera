package com.example.filmera;

import android.content.Context;
import android.widget.Toast;

import com.example.filmera.Models.APIResponse;
import com.example.filmera.Models.DetailsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public class RequestManager {
    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://imdb-api.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManager(Context context) {
        this.context = context;
    }

    public void getMovieNames(OnFetchDataListener listener, String movie_name){
        getMovieNames getMovieNames = retrofit.create(getMovieNames.class);
        Call<APIResponse> call =getMovieNames.getMovies(movie_name);

        try{
            call.enqueue(new Callback<APIResponse>() {
                @Override
                public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                    if (!response.isSuccessful()){
                        Toast.makeText(context, "Couldn't fetch data! Try again later.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    listener.onFetched(response.body(), response.message());
                }

                @Override
                public void onFailure(Call<APIResponse> call, Throwable t) {
                    listener.onError("Request Failed!");
                }

            });
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(context, "Error Occurred!!", Toast.LENGTH_SHORT).show();
        }

    }
    public void getMovieDetails(OnFetchDetailListener listener, String movie_id){
        getMovieDetails getMovieDetails = retrofit.create(getMovieDetails.class);
        Call<DetailsResponse> call =getMovieDetails.callMovieDetails(movie_id);

        try{
            call.enqueue(new Callback<DetailsResponse>() {
                @Override
                public void onResponse(Call<DetailsResponse> call, Response<DetailsResponse> response) {
                    if (!response.isSuccessful()){
                        Toast.makeText(context, "Couldn't fetch data! Try again later.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    listener.onFetched(response.body(), response.message());
                }

                @Override
                public void onFailure(Call<DetailsResponse> call, Throwable t) {
                    listener.onError("Request Failed!");
                }

            });
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(context, "Error Occurred!!", Toast.LENGTH_SHORT).show();
        }

    }


    public interface getMovieNames {
        @Headers({
                "Accept: application/json"
        })
        @GET("API/Search/k_ao4esxeo/{movie}")
        Call<APIResponse> getMovies(
                @Path("movie") String movie
        );
    }
    public interface getMovieDetails {
        @Headers({
                "Accept: application/json",
        })
        @GET("es/API/Title/k_ao4esxeo/{movie_id}")
        Call<DetailsResponse> callMovieDetails(
                @Path("movie_id") String movie_id
        );
    }
}
