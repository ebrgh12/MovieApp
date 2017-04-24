package com.movieapp.serviceInterface;

import com.movieapp.constants.JsonConstants;
import com.movieapp.model.responseModel.popularMovies.PopularMain;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by giris on 2/11/2017.
 */

public interface PopularMovies {
    @GET(JsonConstants.POPULAR_MOVIES)
    Call<PopularMain> getMovies(@QueryMap Map<String,String> apiKe,
                                @QueryMap Map<String,String> pageId);
}
