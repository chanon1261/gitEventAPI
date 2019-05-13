package com.example.android.test.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.databinding.library.baseAdapters.BR
import com.example.android.test.MainApp
import com.example.android.test.databinding.EventItemBinding
import com.example.android.test.model.Event
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.event_item.view.*


class EvenGitAdapter(val context: Context) : RecyclerView.Adapter<EvenGitAdapter.EventdapterViewHolder>() {


    private var items: MutableList<Event> = mutableListOf()

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventdapterViewHolder {
        val layoutInflator: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        return EventdapterViewHolder(
                EventItemBinding.inflate(
                        layoutInflator,
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: EventdapterViewHolder, position: Int) = holder.bind(items[position])

    class EventdapterViewHolder(val binding: EventItemBinding) : RecyclerView.ViewHolder(binding.root) {

        val context: Context = binding.root.context

        fun bind(item: Event) {
            binding.setVariable(BR.id, item.actor.id.toString())
            binding.setVariable(BR.login, item.actor.login)
            binding.setVariable(BR.display, item.actor.display_login)
            binding.setVariable(BR.url, item.actor.url)
            Picasso.with(MainApp.instance.applicationContext).load(item.actor.avatar_url).into(itemView.img)

        }

    }

    fun loadData(data: MutableList<Event>) {
        this.items = data
        notifyDataSetChanged()
    }


}