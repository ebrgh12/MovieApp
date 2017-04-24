package com.movieapp.model.responseModel.popularMovies;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;
import com.movieapp.model.responseModel.popularMovies.result.PopularResult;

import java.util.List;

/**
 * Created by giris on 2/11/2017.
 */

@JsonObject
public class PopularMain {
    @JsonField(name = "page")
    private Integer page;
    @JsonField(name = "results")
    private List<PopularResult> results = null;
    @JsonField(name = "total_results")
    private Integer totalResults;
    @JsonField(name = "total_pages")
    private Integer totalPages;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<PopularResult> getResults() {
        return results;
    }

    public void setResults(List<PopularResult> results) {
        this.results = results;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

}
