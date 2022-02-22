package com.example.demo_movie.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class DetailFilmResModel implements Serializable {
    @SerializedName("id")
    private int id;

    @SerializedName("results")
    private List<Result> listResult;

    public List<Result> getListResult() {
        return listResult;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setListResult(List<Result> listResult) {
        this.listResult = listResult;
    }

    public static class Result implements Serializable {
        @SerializedName("key")
        private String key;

        public String getKey() {
            return key;
        }
    }
}
