package com.example.android.test.service

import com.example.android.test.MainApp
import com.example.android.test.model.Repository
import rx.Observable
import java.util.ArrayList
import javax.inject.Inject

class GitHubService() {

    @Inject
    lateinit var gitHubApiService: GitHubApiService

    init {
        MainApp.graph.inject(this)
    }

    fun searchRepositories(query: String): Observable<List<Repository>> {
        return if (query.isBlank()) {
            Observable.just(ArrayList())
        } else {
            gitHubApiService.searchRepositories(query).map { it.items }
        }
    }

    fun getRepository(owner: String, repository: String) = gitHubApiService.getRepository(owner, repository)

    fun getRepositoryReadme(owner: String, repository: String) = gitHubApiService.getRepositoryReadme(owner, repository)

    fun updateGist(updateGist: String) = gitHubApiService.updateGist(updateGist)

    fun getEvent(q: String) = gitHubApiService.getEvent(q)
}