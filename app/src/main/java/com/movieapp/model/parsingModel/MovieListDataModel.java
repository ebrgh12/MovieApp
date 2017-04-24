package com.movieapp.model.parsingModel;

/**
 * Created by giris on 2/12/2017.
 */

public class MovieListDataModel {
    String imagePath;
    String movieName;
    String description;
    Double userRating;
    String releaseDate;
    Double popularity;

    public MovieListDataModel(String imagePath, String movieName, String description,
                              Double userRating, String releaseDate, Double popularity) {
        this.imagePath = imagePath;
        this.movieName = movieName;
        this.description = description;
        this.userRating = userRating;
        this.releaseDate = releaseDate;
        this.popularity = popularity;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getUserRating() {
        return userRating;
    }

    public void setUserRating(Double userRating) {
        this.userRating = userRating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

}
