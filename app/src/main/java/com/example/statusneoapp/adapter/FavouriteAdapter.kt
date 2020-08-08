package com.example.statusneoapp.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.statusneoapp.R
import com.example.statusneoapp.model.User
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.item_swipe_card.view.*


/**
 * List adapter to bind data for favourite user list
 */

private const val TAG = "FavouriteAdapter"

class FavouriteAdapter(
    private val context: Context,
    private var userList: ArrayList<User>
) : RecyclerView.Adapter<FavouriteAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_swipe_card, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(position)

    }


    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(position: Int) {

            itemView?.toggle_button_layout?.setToggled(R.id.toggle_user_name, true)
            itemView?.tv_user_info_hint?.text = context.getString(R.string.user_name_hint)
            itemView?.tv_user_info_data?.text = userList[position].username

            itemView?.toggle_button_layout?.onToggledListener =
                { toggleButtonLayout, toggle, selected ->

                    when (toggle.id) {
                        R.id.toggle_user_name -> {
                            itemView?.tv_user_info_hint?.text =
                                context.getString(R.string.user_name_hint)
                            itemView?.tv_user_info_data?.text = userList[position].username
                        }
                        R.id.toggle_user_details -> {
                            var userName = userList[position].name
                            itemView?.tv_user_info_hint?.text =
                                context.getString(R.string.user_details_hint)
                            itemView?.tv_user_info_data?.text =
                                userName?.title + " " + userName?.first + " " + userName?.last
                        }
                        R.id.toggle_user_address -> {
                            var userAddress = userList[position].location
                            itemView?.tv_user_info_hint?.text =
                                context.getString(R.string.user_address_hint)
                            itemView?.tv_user_info_data?.text =
                                userAddress?.street + ",\n" + userAddress?.city + "," + userAddress?.zip
                        }
                        R.id.toggle_user_contact -> {
                            itemView?.tv_user_info_hint?.text =
                                context.getString(R.string.user_phone_hint)
                            itemView?.tv_user_info_data?.text = userList[position].cell
                        }
                        R.id.toggle_user_password -> {
                            itemView?.tv_user_info_hint?.text =
                                context.getString(R.string.user_password_hint)
                            itemView?.tv_user_info_data?.text = userList[position].password
                        }

                    }
                }

            itemView.iv_user_profile?.let {
                Glide.with(context).load(userList[position].picture).circleCrop().placeholder(R.drawable.ic_account).into(it)

            }


        }


    }


}