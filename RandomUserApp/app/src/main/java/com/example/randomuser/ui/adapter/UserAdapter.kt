package com.example.randomuser.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.randomuser.R
import com.example.randomuser.model.Results
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class UserAdapter(var userList: ArrayList<Results>, var onUserClickListener: OnUserClickListener): RecyclerView.Adapter<UserAdapter.ViewHolder>(), Filterable{

    var userFilterList = ArrayList<Results>(userList)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.ViewHolder {
       val view= LayoutInflater.from(parent.context).inflate(R.layout.item_user,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = userList.size

    override fun onBindViewHolder(holder: UserAdapter.ViewHolder, position: Int) {
      holder.userName.text = "${userList[position].name.first} ${userList[position].name.last}"
      holder.email.text = userList[position].email
        Picasso.get().load(userList[position].picture.large).into(holder.userImage)

        ViewCompat.setTransitionName(holder.userImage,"${userList[position].name.first} ${userList[position].name.last}")

        holder.itemView.setOnClickListener {
            onUserClickListener.onUserlickListener(userList[position],holder.userImage)
        }
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val userName= itemView.findViewById<TextView>(R.id.tv_item_username)
        val email= itemView.findViewById<TextView>(R.id.tv_item_email)
        val userImage= itemView.findViewById<CircleImageView>(R.id.profile_image)

    }


    override fun getFilter(): Filter {
        return object: Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
               val filteredList = ArrayList<Results>()
                if(constraint == null || constraint.isEmpty()){
                    filteredList.addAll(userFilterList)
                }else{
                    val filteredPattern = constraint.toString().toLowerCase().trim()

                    for(results: Results in userFilterList){
                        val fullName= "${results.name.first} ${results.name.last}"

                        if(fullName.toLowerCase().contains(filteredPattern)){
                            filteredList.add(results)
                        }
                    }
                }

                return FilterResults().apply { values = filteredList }
            }

            override fun publishResults(p0: CharSequence?, results: FilterResults?) {
                userList.clear()
                userList.addAll(results?.values as ArrayList<Results>)
                notifyDataSetChanged()
            }

        }
    }

    interface OnUserClickListener{
        fun onUserlickListener(results: Results, sharedImageView: ImageView)

    }
}