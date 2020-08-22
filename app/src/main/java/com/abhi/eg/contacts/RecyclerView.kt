package com.abhi.eg.contacts

import android.os.Bundle
import android.os.Handler
import android.renderscript.Script
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_recycler_view.*
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 */
class RecyclerView : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recycler_view, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mySwipeRefreshLayout.setOnRefreshListener {
Handler().postDelayed({

    Toast.makeText(
        this.activity,
        "updated!",
        Toast.LENGTH_SHORT
    ).show()
    mySwipeRefreshLayout.isRefreshing=false

},2000)
        }
        rec.setHasFixedSize(true)
        rec.layoutManager = LinearLayoutManager(activity)


        launch{
            context?.let {
                val contact = AppDatabase(it).getContactDao().getAllContacts()
                rec.adapter =
                    ContactAdapter(contact)
            }
        }
        facADD.setOnClickListener {
            view!!.findNavController().navigate(R.id.addContact)
        }

    }

}
