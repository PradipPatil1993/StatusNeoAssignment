package com.example.statusneoapp.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.bumptech.glide.Glide
import com.example.statusneoapp.R
import com.example.statusneoapp.model.User
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.item_swipe_card.view.*

private const val TAG = "UserAdapter"

/**
 * List adapter to bind data for  user list
 */


class UserAdapter(
    private val data: ArrayList<User>,
    private val context: Context
) : BaseAdapter() {


    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(position: Int): Any {
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View? {
        var itemView = convertView
        if (itemView == null) {
            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            itemView = inflater.inflate(R.layout.item_swipe_card, parent, false)
        }



        itemView?.toggle_button_layout?.setToggled(R.id.toggle_user_name, true)
        itemView?.tv_user_info_hint?.text = context.getString(R.string.user_name_hint)
        itemView?.tv_user_info_data?.text = data[position].username

        itemView?.toggle_button_layout?.onToggledListener =
            { toggleButtonLayout, toggle, selected ->

               when(toggle.id){
                   R.id.toggle_user_name ->{
                       itemView?.tv_user_info_hint?.text = context.getString(R.string.user_name_hint)
                       itemView?.tv_user_info_data?.text = data[position].username
                   }
                   R.id.toggle_user_details ->{
                       var userName = data[position].name
                       itemView?.tv_user_info_hint?.text = context.getString(R.string.user_details_hint)
                       itemView?.tv_user_info_data?.text = userName?.title +" "+ userName?.first +" "+ userName?.last
                   }
                   R.id.toggle_user_address ->{
                       var userAddress = data[position].location
                       itemView?.tv_user_info_hint?.text = context.getString(R.string.user_address_hint)
                       itemView?.tv_user_info_data?.text = userAddress?.street +",\n"+ userAddress?.city +","+ userAddress?.zip
                   }
                   R.id.toggle_user_contact ->{
                       itemView?.tv_user_info_hint?.text = context.getString(R.string.user_phone_hint)
                       itemView?.tv_user_info_data?.text = data[position].cell
                   }
                   R.id.toggle_user_password ->{
                       itemView?.tv_user_info_hint?.text = context.getString(R.string.user_password_hint)
                       itemView?.tv_user_info_data?.text = data[position].password
                   }

               }
            }

        itemView?.iv_user_profile?.let {
            Log.d(TAG, "getView: ${data[position].picture}")
            Glide.with(context).load(data[position].picture).circleCrop().placeholder(R.drawable.ic_account).into(it)
        };

        return itemView
    }

}