package com.example.demo_movie.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class FilmResModel implements Serializable {
    @SerializedName("page")
    private int page;

    @SerializedName("results")
    private List<Result> listResult;

    public int getPage() {
        return page;
    }

//    public void setPage(int page) {
//        this.page = page;
//    }
//
//    public void setListResult(List<Result> listResult) {
//        this.listResult = listResult;
//    }

    public List<Result> getListResult() {
        return listResult;
    }

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

        public String getDropback() {
            return dropback;
        }

        public String getId() {
            return id;
        }

        public String getLanguage() {
            return language;
        }

        public String getPosterPath() {
            return posterPath;
        }

        public String getOriginaleTitle() {
            return originaleTitle;
        }

        public String getTitle() {
            return title;
        }

        public String getOverview() {
            return overview;
        }

        public String getTime() {
            return time;
        }

        public String getVote() {
            return vote;
        }

        public String getVoteCount() {
            return voteCount;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }
    }
}

