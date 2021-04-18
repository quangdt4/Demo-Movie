package com.example.demo_movie.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilmResModel implements Serializable {
    @SerializedName("page")
    private int page;

    @SerializedName("results")
    private List<Result> listResult;

    @Getter
    @Setter
    public static class Result implements Serializable {
        private boolean isSelected;

        public boolean isSelected() {
            return isSelected;
        }

        @SerializedName("id")
        private String id;

        @SerializedName("poster_path")
        private String posterPath;

        @SerializedName("backdrop_path")
        private String dropback;

        @SerializedName("title")
        private String title;

        @SerializedName("original_title")
        private String originaleTitle;

        @SerializedName("release_date")
        private String time;

        @SerializedName("overview")
        private String overview;

        @SerializedName("vote_average")
        private String vote;

        @SerializedName("original_language")
        private String language;

        @SerializedName("vote_count")
        private String voteCount;
    }
}

