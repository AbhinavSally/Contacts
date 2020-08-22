package com.abhi.eg.contacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.abhi.eg.contacts.Contact
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import kotlinx.android.synthetic.main.contact_data.view.*

class ContactAdapter(private val contacts: List<Contact>) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.contact_data, parent, false)
        )
    }

    override fun getItemCount() = contacts.size

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.view.recNAME.text = contacts[position].name
        holder.view.recNUMBER.text = contacts[position].phoneNumber
        Glide.with(holder.view)
            .load(contacts[position].image)
            .transform(CenterCrop())
            .into(holder.view.ivProfileShow)


      holder.view.setOnClickListener {
            val action = RecyclerViewDirections.actionRecyclerViewToShowContact()
            action.contact = contacts[position]
            Navigation.findNavController(it).navigate(action)
        }


      holder.view.btnOptionShow.setOnClickListener {
       
          val action = RecyclerViewDirections.actionRecyclerViewToEditContact()
          action.contact = contacts[position]

          Navigation.findNavController(it).navigate(action)
      }

    }

    class ContactViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}