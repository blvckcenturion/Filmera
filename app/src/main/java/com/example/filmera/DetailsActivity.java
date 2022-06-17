package com.example.filmera;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.filmera.Models.DetailsResponse;
import com.google.android.material.card.MaterialCardView;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {
    TextView textView_movie_detail_name, textView_movie_detail_year, textView_movie_detail_length, textView_movie_detail_rating, textView_movie_detail_votes, textView_movie_detail_plot, textView_movie_detail_release, textView_movie_detail_companies, textView_movie_detail_genres, textView_movie_detail_awards;
    TextView textView_movie_detail_budget, textView_movie_detail_opening, textView_movie_detail_total;
    RecyclerView recycler_cast;
    ImageView imageView_movie_detail_poster;
    DetailsAdapter detailsAdapter;
    ScrollView details_container;
    MaterialCardView basicInfo, basicInfo2, plot, boxInfo, cast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        basicInfo = findViewById(R.id.basic_info);
        basicInfo2 = findViewById(R.id.basic_info_2);
        plot = findViewById(R.id.plot);
        boxInfo = findViewById(R.id.box_info);
        cast = findViewById(R.id.cast);

        textView_movie_detail_name = findViewById(R.id.textView_movie_detail_name);
        textView_movie_detail_year = findViewById(R.id.textView_movie_detail_year);
        textView_movie_detail_length = findViewById(R.id.textView_movie_detail_length);
        textView_movie_detail_rating = findViewById(R.id.textView_movie_detail_rating);
        textView_movie_detail_votes = findViewById(R.id.textView_movie_detail_votes);
        textView_movie_detail_plot = findViewById(R.id.textView_movie_detail_plot);
        textView_movie_detail_companies = findViewById(R.id.textView_movie_companies);
        textView_movie_detail_release = findViewById(R.id.textView_movie_detail_release);
        textView_movie_detail_genres = findViewById(R.id.textView_movie_genres);
        textView_movie_detail_awards = findViewById(R.id.textView_movie_awards);
        textView_movie_detail_opening = findViewById(R.id.textView_movie_opening);
        textView_movie_detail_budget = findViewById(R.id.textView_movie_budget);
        textView_movie_detail_total = findViewById(R.id.textView_movie_total);
        recycler_cast = findViewById(R.id.recycler_cast);
        imageView_movie_detail_poster = findViewById(R.id.imageView_movie_detail_poster);
        details_container = findViewById(R.id.details_container);

        String id = getIntent().getStringExtra("movie_id");

        RequestManager manager = new RequestManager(this);
        manager.getMovieDetails(listener, id);
        cast.setVisibility(View.GONE);
        basicInfo.setVisibility(View.GONE);
        basicInfo2.setVisibility(View.GONE);
        plot.setVisibility(View.GONE);
        boxInfo.setVisibility(View.GONE);
    }

    private final OnFetchDetailListener listener = new OnFetchDetailListener() {
        @Override
        public void onFetched(DetailsResponse response, String message) {
            if (response==null){
                Toast.makeText(DetailsActivity.this, "No Data Found!", Toast.LENGTH_SHORT).show();
                return;
            }
            showResult(response);
        }

        @Override
        public void onError(String message) {
            Toast.makeText(DetailsActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

    private void showResult(DetailsResponse response) {

        cast.setVisibility(View.VISIBLE);
        basicInfo.setVisibility(View.VISIBLE);
        basicInfo2.setVisibility(View.VISIBLE);
        plot.setVisibility(View.VISIBLE);
        boxInfo.setVisibility(View.VISIBLE);

        textView_movie_detail_name.setText(response.getTitle());
        textView_movie_detail_year.setText("Año Lanzamiento: "+response.getYear());
        textView_movie_detail_length.setText("Duracion: "+response.getRuntimeStr());
        textView_movie_detail_rating.setText(response.getImDbRating());
        textView_movie_detail_votes.setText(response.getImDbRatingVotes()+" Votos");
        textView_movie_detail_plot.setText(response.getPlotLocal());
        textView_movie_detail_release.setText("Lanzamiento: " + response.getReleaseDate());
        textView_movie_detail_awards.setText("Premios: " + response.getAwards());
        textView_movie_detail_genres.setText("Generos: " +response.getGenres());
        textView_movie_detail_companies.setText("Desarrollada por: " +response.getCompanies());
        textView_movie_detail_budget.setText("Presupuesto: " + response.getBoxOffice().getBudget());
        textView_movie_detail_opening.setText("1era semana: " + response.getBoxOffice().getOpeningWeekendUSA());
        textView_movie_detail_total.setText("Total Recaudado: " + response.getBoxOffice().getCumulativeWorldwideGross());
        try{
            Picasso.get().load(response.getImage()).into(imageView_movie_detail_poster);
        }catch (Exception e){
            e.printStackTrace();
        }

        recycler_cast.setHasFixedSize(true);
        recycler_cast.setLayoutManager(new GridLayoutManager(this, 1));
        detailsAdapter = new DetailsAdapter(this, response.getActorList());
        recycler_cast.setAdapter(detailsAdapter);

        details_container.setVisibility(View.VISIBLE);
    }
}