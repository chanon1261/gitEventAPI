package com.example.android.test.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.EventLog
import com.example.android.test.MainApp
import com.example.android.test.R
import com.example.android.test.adapter.EvenGitAdapter
import com.example.android.test.extension.subscribeOnIo
import com.example.android.test.model.Event
import com.example.android.test.service.GitHubService
import kotlinx.android.synthetic.main.activity_main.*
import rx.android.schedulers.AndroidSchedulers
import java.util.ArrayList
import javax.inject.Inject

class MainActivity : AppCompatActivity()/*AndroidApplication()*/ {

    companion object {
        fun getStartIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

    @Inject
    lateinit var gitHubService: GitHubService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MainApp.graph.inject(this)


        val adapter = EvenGitAdapter(this)
        val layoutManager = LinearLayoutManager(this)
        eventGit?.layoutManager = layoutManager
        eventGit?.adapter = adapter
        adapter.notifyDataSetChanged()

        var dataEvent: List<Event>
        gitHubService.getEvent("events")
                .subscribeOnIo()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    dataEvent = it
                    adapter.loadData(dataEvent as MutableList<Event>)
                }
    }


}
