package com.example.android.test.service

import com.example.android.test.model.Event
import com.example.android.test.model.Repository
import com.example.android.test.model.RepositoryReadme
import com.example.android.test.model.RepositorySearchResults
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable
import retrofit2.http.PUT




interface GitHubApiService {

    @GET("search/repositories")
    fun searchRepositories(@Query("q") query: String): Observable<RepositorySearchResults>

    @GET("/repos/{owner}/{repo}")
    fun getRepository(@Path("owner") owner: String, @Path("repo") repository: String): Observable<Repository>

    @GET("/repos/{owner}/{repo}/readme")
    fun getRepositoryReadme(@Path("owner") owner: String, @Path("repo") repository: String): Observable<RepositoryReadme>

    @PUT("gists/{id}")
    fun updateGist(@Path("id") id: String): Observable<RepositorySearchResults>

    @GET("{q}")
    fun getEvent(@Path("q") id: String): Observable<List<Event>>

}