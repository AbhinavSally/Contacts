package com.abhi.eg.contacts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_show_contact.*

/**
 * A simple [Fragment] subclass.
 */
class ShowContact : BaseFragment() {

        private var contact: Contact? = null

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_show_contact, container, false)
        }

        override fun onActivityCreated(savedInstanceState: Bundle?) {
            super.onActivityCreated(savedInstanceState)

            arguments?.let {
                contact = ShowContactArgs.fromBundle(it).contact
                etDISPLAYNAME.setText(contact?.name)
                etDISPLAYNUMBER.setText(contact?.phoneNumber)
                Glide.with(view!!).load(contact?.image).into(ivProfileDISPLAY)

            }
            bynSure.setOnClickListener {
                view!!.findNavController().navigate(R.id.action_showContact_to_recyclerView)
            }

        }
    }
