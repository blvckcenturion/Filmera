package com.example.filmera.Models;

import java.util.List;

public class DetailsResponse {
    String id = "";
    String title = "";
    String year = "";
    String image = "";
    String runtimeStr = "";
    String imDbRating = "";
    String imDbRatingVotes = "";
    String plotLocal = "";
    String releaseDate = "";
    String companies = "";
    String genres = "";
    String awards = "";
    String trailer = null;

    List<Cast> actorList = null;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getRuntimeStr() {
        return runtimeStr;
    }

    public String getImDbRating() {
        return imDbRating;
    }

    public String getImDbRatingVotes() {
        return imDbRatingVotes;
    }

    public String getImage() {
        return image;
    }

    public String getPlotLocal() {
        return plotLocal;
    }

    public String getTrailer() {
        return trailer;
    }

    public String getReleaseDate() {return releaseDate;}

    public String getCompanies() {return companies;}

    public String getGenres() {return genres;}

    public String getAwards() {return awards;}

    public List<Cast> getActorList() {
        return actorList;
    }
}
