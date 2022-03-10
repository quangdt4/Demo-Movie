package com.example.demo_movie.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.Setter;

//@Getter
//@Setter
//@AllArgsConstructor
public class SearchReqModel implements Serializable {
    @SerializedName("page")
    private int page;

    @SerializedName("results")
    private List<FilmResModel.Result> listResult;

    public int getPage() {
        return page;
    }

    public List<FilmResModel.Result> getListResult() {
        return listResult;
    }


}
