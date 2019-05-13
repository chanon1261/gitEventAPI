package com.example.android.test.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.databinding.library.baseAdapters.BR
import com.example.android.test.extension.loadUrl
import com.example.android.test.model.Repository
import com.ocpsoft.pretty.time.PrettyTime
import kotlinx.android.synthetic.main.repository_item.view.*
import java.util.ArrayList
import com.example.android.test.databinding.RepositoryItemBinding
import com.example.android.test.extension.format

class RepositoryAdapter(val context: Context) : RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {

    var repositories: List<Repository> = ArrayList()

    override fun getItemCount() = repositories.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val layoutInflator: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        return RepositoryViewHolder(RepositoryItemBinding.inflate(layoutInflator, parent, false))
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) = holder.bind(repositories.get(position))

    fun loadRepositories(repositories: List<Repository>) {
        this.repositories = repositories
        notifyDataSetChanged()
    }

    class RepositoryViewHolder(val binding: RepositoryItemBinding) : RecyclerView.ViewHolder(binding.root) {

        val context: Context = binding.root.context

        fun bind(repository: Repository) {
//            binding.setVariable(BR.actor, repository)
//            binding.setVariable(BR.type, PrettyTime().format(repository.pushed_at))
//            itemView.repositoryItemImage.loadUrl("https://www.google.co.th/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png")
            //itemView.repositoryItemRootLayout.setOnClickListener { context.startActivity(RepositoryDetailActivity.getIntent(context, repository)) }
        }
    }
}