package com.example.statusneoapp.ui.fragments.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.statusneoapp.R
import com.example.statusneoapp.adapter.FavouriteAdapter

class FavouriteFragment : Fragment() {

    private val favouriteViewModel: FavouriteViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        initView(root)
        favouriteViewModel.fetchAllUsersList(requireContext()).observe(viewLifecycleOwner, Observer {
            recyclerView.adapter = FavouriteAdapter(requireContext(), it)
        })
        return root
    }

    private fun initView(root: View?) {
        recyclerView = root?.findViewById(R.id.recycler_fav_list) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.itemAnimator = DefaultItemAnimator()
    }
}