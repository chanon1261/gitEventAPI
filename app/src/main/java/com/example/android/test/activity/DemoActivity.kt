package com.example.android.test.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.android.test.MainApp
import com.example.android.test.R
import com.example.android.test.adapter.RepositoryAdapter
import com.example.android.test.extension.hide
import com.example.android.test.extension.show
import com.example.android.test.extension.subscribeOnIo
import com.example.android.test.extension.subscribeUntilDestroy
import com.example.android.test.service.GitHubService
import com.jakewharton.rxbinding.widget.RxTextView
import com.trello.rxlifecycle.components.support.RxAppCompatActivity
import kotlinx.android.synthetic.main.activity_demo.*
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class DemoActivity : RxAppCompatActivity() {

    companion object {
        fun getStartIntent(context: Context) = Intent(context, DemoActivity::class.java)
    }

    @Inject
    lateinit var gitHubService: GitHubService

    lateinit var repositoryAdapter: RepositoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo)
        MainApp.graph.inject(this)

        //setSupportActionBar(toolbar)
        setUpRecyclerView()
        setUpSearchView()
    }

    fun setUpRecyclerView() {
        repositoryAdapter = RepositoryAdapter(this)
        mainResultsRecycler.adapter = repositoryAdapter
        mainResultsRecycler.layoutManager = LinearLayoutManager(this)
    }

    fun setUpSearchView() {
        val searchEditText = mainSearchCardView.getEditText()
        searchEditText.setText("kotlin")
        searchEditText.setSelection(searchEditText.text.length);
        searchEditText.setHint(R.string.search_repositories)
        RxTextView.textChanges(searchEditText)
                .filter {
                    it.length > 3
                }
                .doOnNext { mainResultsSpinner.show() }
                .sample(1, TimeUnit.SECONDS)
                .switchMap { gitHubService.searchRepositories(it.toString()).subscribeOnIo() }
                .subscribeUntilDestroy(this) {
                    onNext {
                        mainResultsSpinner.hide()
                        repositoryAdapter.loadRepositories(it)
                    }
                    onError {
                        Timber.e(it, "Failed to load repositories")
                        mainResultsSpinner.hide()
                    }
                }
    }
}
