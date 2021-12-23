package com.example.sixthtask

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class RecyclerAdapter(private val people: ArrayList<Person>) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private var onClickListener: InterfaceClick.ClickedItemHandler? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var nameTextView: TextView? = null
        var phoneTextView: TextView? = null
        var progressBar: ProgressBar? = null
        var avatarImage: ImageView? = null

        init {
            nameTextView = view.findViewById(R.id.name_contact)
            phoneTextView = view.findViewById(R.id.phone_contact)
            progressBar = view.findViewById(R.id.progressBar)
            avatarImage = view.findViewById(R.id.avatarImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val item =
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        onClickListener = parent.context as InterfaceClick.ClickedItemHandler
        return ViewHolder(item)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener { onClickListener!!.openContactDetails(people[position]) }
        holder.nameTextView?.text = people[position].name + " " + people[position].secondName
        holder.phoneTextView?.text = people[position].phone
        Picasso.get().load("https://picsum.photos/id/" + people[position].avatarId + "/200/200")
            .into(holder.avatarImage);
    }

    override fun getItemCount() = people.size
}
