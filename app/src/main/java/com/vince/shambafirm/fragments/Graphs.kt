package com.vince.shambafirm.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vince.shambafirm.R
import com.vince.shambafirm.viewModels.GraphsViewModel

class Graphs : Fragment() {

    companion object {
        fun newInstance() = Graphs()
    }

    private lateinit var viewModel: GraphsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_graphs, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(GraphsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}