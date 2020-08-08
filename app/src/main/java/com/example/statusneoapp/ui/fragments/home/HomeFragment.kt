package com.example.statusneoapp.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.daprlabs.cardstack.SwipeDeck
import com.example.statusneoapp.R
import com.example.statusneoapp.adapter.UserAdapter
import com.example.statusneoapp.model.User
import com.example.statusneoapp.utility.AppUtility


private const val TAG = "HomeFragment"


/**
 * Fragment use to show swipe cards
 */
class HomeFragment : Fragment(), SwipeDeck.SwipeEventCallback {

    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var cardStack: SwipeDeck
    private  var userDataList = ArrayList<User>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_home, container, false)
        initViews(root)

        if(AppUtility.isNetworkAvailable(requireContext())){
            homeViewModel.loadUserData() // set default first card
            homeViewModel.loadUserData() // set default second card
            homeViewModel.loadUserData() // set default third card
        }else{
            AppUtility.showSnackBar(root, getString(R.string.network_error))
        }


        homeViewModel.getUserData().observe(viewLifecycleOwner, Observer {
            userDataList.add(it)
            val adapter = context?.let { it -> UserAdapter(userDataList, it) }
            cardStack.setAdapter(adapter)

        })
        return root
    }

    private fun initViews(root: View) {
        cardStack = root.findViewById(R.id.swipe_deck)
        cardStack.setEventCallback(this)



    }

    override fun cardSwipedLeft(position: Int) {
        if(AppUtility.isNetworkAvailable(requireContext())){
            userDataList.remove(userDataList[position])
            homeViewModel.loadUserData()
        }else{
            AppUtility.showSnackBar(requireView(), getString(R.string.network_error))
        }
    }

    override fun cardActionUp() {

    }

    override fun cardsDepleted() {
    }

    override fun cardActionDown() {
    }

    override fun cardSwipedRight(position: Int) {
        if(AppUtility.isNetworkAvailable(requireContext())){
            homeViewModel.insertUser(requireContext(),userDataList[position])
            userDataList.remove(userDataList[position])
            homeViewModel.loadUserData()
        }else{
            AppUtility.showSnackBar(requireView(), getString(R.string.network_error))
        }

    }
}