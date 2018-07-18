package com.bananacoding.android.pin_assistant_android.service

import com.bananacoding.android.pin_assistant_android.model.Repository
import com.bananacoding.android.pin_assistant_android.model.RepositoryReadme
import com.bananacoding.android.pin_assistant_android.model.RepositorySearchResults
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable


interface GitHubApiService {

    @GET("search/repositories")
    fun searchRepositories(@Query("q") query: String): Observable<RepositorySearchResults>

    @GET("/repos/{owner}/{repo}")
    fun getRepository(@Path("owner") owner: String, @Path("repo") repository: String): Observable<Repository>

    @GET("/repos/{owner}/{repo}/readme")
    fun getRepositoryReadme(@Path("owner") owner: String, @Path("repo") repository: String): Observable<RepositoryReadme>

}