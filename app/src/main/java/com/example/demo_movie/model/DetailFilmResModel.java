package com.example.demo_movie.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetailFilmResModel implements Serializable {
    @SerializedName("id")
    private int id;

    @SerializedName("results")
    private List<Result> listResult;

    @Getter
    @Setter
    public static class Result implements Serializable {
        @SerializedName("key")
        private String key;
    }
}
