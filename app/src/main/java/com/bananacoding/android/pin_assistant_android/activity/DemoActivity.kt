package com.bananacoding.android.pin_assistant_android.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.bananacoding.android.pin_assistant_android.MainApp
import com.bananacoding.android.pin_assistant_android.R
import com.bananacoding.android.pin_assistant_android.adapter.RepositoryAdapter
import com.bananacoding.android.pin_assistant_android.extension.hide
import com.bananacoding.android.pin_assistant_android.extension.show
import com.bananacoding.android.pin_assistant_android.extension.subscribeOnIo
import com.bananacoding.android.pin_assistant_android.extension.subscribeUntilDestroy
import com.bananacoding.android.pin_assistant_android.service.GitHubService
import com.jakewharton.rxbinding.widget.RxTextView
import com.trello.rxlifecycle.components.support.RxAppCompatActivity
import io.reactivex.internal.util.HalfSerializer.onNext
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
        searchEditText.setSelection(searchEditText.getText().length);
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
