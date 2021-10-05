package com.kb.nytimesdemo.data

import com.kb.nytimes.model.ArticlesResponse
import com.kb.nytimes.model.SearchedArticlesResponse
import com.kb.nytimesdemo.utils.API_KEY
import com.kb.nytimesdemo.utils.API_KEY_VALUE
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ArticlesApi {
    //https://api.nytimes.com/svc/mostpopular/v2/emailed/7.json?api-key=
    @GET("mostpopular/v2/emailed/{noOfDays}.json")
    suspend fun mostEmailedArticles(
        @Path("noOfDays") noOfDays: Int,
        @Query(API_KEY) timeSpan: String? = API_KEY_VALUE
    ): ArticlesResponse

    //https://api.nytimes.com/svc/search/v2/articlesearch.json?q=t&facet=true&facet_fields=news_desk&fq=World&api-key=
    @GET("search/v2/articlesearch.json") //For the same of simplicity, added static fields herw
    suspend fun searchArticles(
        @Query("q") searchResult: String,
        @Query("facet") facet: Boolean? = true,
        @Query("facet_fields") facet_field: String? = "news_desk",
        @Query("fq") slectedDesk: String? = null,
        @Query(API_KEY) timeSpan: String? = API_KEY_VALUE
    ): SearchedArticlesResponse

    //https://api.nytimes.com/svc/mostpopular/v2/shared/1/facebook.json?api-key=
    @GET("mostpopular/v2/shared/{noOfDays}/facebook.json")
    suspend fun mostSharedArticles(
        @Path("noOfDays") noOfDays: Int,
        @Query(API_KEY) timeSpan: String? = API_KEY_VALUE
    ): ArticlesResponse

    //https://api.nytimes.com/svc/mostpopular/v2/viewed/1.json?api-key=
    @GET("mostpopular/v2/viewed/{noOfDays}.json")
    suspend fun mostViewedArticles(
        @Path("noOfDays") noOfDays: Int,
        @Query(API_KEY) timeSpan: String? = API_KEY_VALUE
    ): ArticlesResponse
}