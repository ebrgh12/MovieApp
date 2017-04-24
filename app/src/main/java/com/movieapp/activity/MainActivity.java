package com.movieapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.aurae.retrofit2.LoganSquareConverterFactory;
import com.movieapp.R;
import com.movieapp.adapter.MovieListAdapter;
import com.movieapp.constants.JsonConstants;
import com.movieapp.model.parsingModel.MovieListDataModel;
import com.movieapp.model.responseModel.popularMovies.PopularMain;
import com.movieapp.serviceInterface.PopularMovies;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    PopularMovies popularMovies;
    Map<String,String> api_key = new HashMap<String, String>();
    Map<String,String> page_number = new HashMap<String, String>();

    List<MovieListDataModel> movieListDataModels = new ArrayList<MovieListDataModel>();

    MovieListAdapter movieListAdapter;

    Integer pageCount = 0;
    LinearLayoutManager mLayoutManager;
    RecyclerView movieList;
    SwipyRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * retrofit initialization and also the service
         * interface of retrofit
         * */
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(JsonConstants.BASE_URL)
                .addConverterFactory(LoganSquareConverterFactory.create())
                .build();
        popularMovies = retrofit.create(PopularMovies.class);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        movieList = (RecyclerView) findViewById(R.id.movie_list);
        mLayoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
        movieList.setLayoutManager(mLayoutManager);

        swipeRefreshLayout = (SwipyRefreshLayout) findViewById(R.id.swipyrefreshlayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                LoadMoreData();
            }
        });

        retrieveMovies();

    }

    private void retrieveMovies() {
        pageCount = pageCount + 1;
        api_key.put("api_key","8f2b52cb3578ed865acfbd4d642dc062");
        page_number.put("page",String.valueOf(pageCount));

        Call<PopularMain> getPopularMovies = popularMovies.getMovies(api_key,page_number);
        getPopularMovies.enqueue(new Callback<PopularMain>() {
            @Override
            public void onResponse(Call<PopularMain> call, Response<PopularMain> response) {
                if(response.isSuccess()){
                    if(response.body().getResults().size() != 0 &&
                            response.body().getResults() != null){
                        for(int i=0;i<response.body().getResults().size();i++){
                            movieListDataModels.add(new MovieListDataModel(
                                    response.body().getResults().get(i).getPosterPath(),
                                    response.body().getResults().get(i).getOriginalTitle(),
                                    response.body().getResults().get(i).getOverview(),
                                    response.body().getResults().get(i).getVoteAverage(),
                                    response.body().getResults().get(i).getReleaseDate(),
                                    response.body().getResults().get(i).getPopularity()));
                        }
                        movieListAdapter = new MovieListAdapter(
                                MainActivity.this,MainActivity.this,
                                movieListDataModels);
                        movieList.setAdapter(movieListAdapter);
                    }else {
                        Toast.makeText(MainActivity.this, "no data available", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(MainActivity.this, "no data available", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PopularMain> call, Throwable t) {
                Toast.makeText(MainActivity.this, "no data available", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        switch (id){
            case R.id.action_settings:
                Collections.sort(movieListDataModels, new Comparator<MovieListDataModel>() {
                    @Override
                    public int compare(MovieListDataModel movieListDataModel, MovieListDataModel t1) {
                        //app queries the /movie/popular
                        return movieListDataModel.getPopularity().compareTo(t1.getPopularity());
                    }
                });
                Collections.reverse(movieListDataModels);
                movieListAdapter.notifyDataSetChanged();

                break;
            case R.id.top_rated:
                Collections.sort(movieListDataModels, new Comparator<MovieListDataModel>() {
                    @Override
                    public int compare(MovieListDataModel movieListDataModel, MovieListDataModel t1) {
                       // query the /movie/top_rated API endpoint
                        return movieListDataModel.getUserRating().compareTo(t1.getUserRating());
                    }
                });
                Collections.reverse(movieListDataModels);
                movieListAdapter.notifyDataSetChanged();

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void LoadMoreData() {

        pageCount = pageCount + 1;
        api_key.put("api_key","8f2b52cb3578ed865acfbd4d642dc062");
        page_number.put("page",String.valueOf(pageCount));

        Call<PopularMain> getPopularMovies = popularMovies.getMovies(api_key,page_number);
        getPopularMovies.enqueue(new Callback<PopularMain>() {
            @Override
            public void onResponse(Call<PopularMain> call, Response<PopularMain> response) {
                swipeRefreshLayout.setRefreshing(false);
                if(response.isSuccess()){
                    if(response.body().getResults().size() != 0 &&
                            response.body().getResults() != null){
                        for(int i=0;i<response.body().getResults().size();i++){

                            movieListDataModels.add(new MovieListDataModel(
                                    response.body().getResults().get(i).getPosterPath(),
                                    response.body().getResults().get(i).getOriginalTitle(),
                                    response.body().getResults().get(i).getOverview(),
                                    response.body().getResults().get(i).getVoteAverage(),
                                    response.body().getResults().get(i).getReleaseDate(),
                                    response.body().getResults().get(i).getPopularity()));
                        }
                        movieListAdapter.notifyDataSetChanged();
                    }else {
                        Toast.makeText(MainActivity.this, "no data available", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(MainActivity.this, "no data available", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PopularMain> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(MainActivity.this, "no data available", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void callDetailPage(String imagePath, Double userRating, String movieName,
                               String description, String releaseDate) {
        Intent intent = new Intent(MainActivity.this,MovieDetailActivity.class);
        intent.putExtra("image_name",imagePath);
        intent.putExtra("userRating", userRating);
        intent.putExtra("movieName",movieName);
        intent.putExtra("description",description);
        intent.putExtra("releaseDate",releaseDate);
        startActivity(intent);
    }
}
