package com.example.android.test.service

import com.example.android.test.MainApp
import com.example.android.test.model.Repository
import rx.Observable
import java.util.ArrayList
import javax.inject.Inject

open class GitHubService {

    @Inject
    lateinit var gitHubApiService: GitHubApiService

    constructor() {
        MainApp.graph.inject(this)
    }

    open fun searchRepositories(query: String): Observable<List<Repository>> {
        if (query.isBlank()) {
            return Observable.just(ArrayList())
        } else {
            return gitHubApiService.searchRepositories(query).map { it.items }
        }
    }

    fun getRepository(owner: String, repository: String) = gitHubApiService.getRepository(owner, repository)

    fun getRepositoryReadme(owner: String, repository: String) = gitHubApiService.getRepositoryReadme(owner, repository)

    fun updateGist(updateGist: String) = gitHubApiService.updateGist(updateGist)
}