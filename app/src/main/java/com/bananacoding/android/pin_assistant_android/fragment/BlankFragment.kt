package com.bananacoding.android.pin_assistant_android.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.bananacoding.android.pin_assistant_android.R
import com.bananacoding.android.pin_assistant_android.activity.MainActivity
import com.trello.rxlifecycle.components.support.RxFragment

class BlankFragment : RxFragment() {

    private lateinit var mainActivity: MainActivity

    companion object {
        fun fragment(mainActivity: MainActivity): BlankFragment {
            val fragment = BlankFragment()
            fragment.mainActivity = mainActivity
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }


}
