package com.abhi.eg.contacts

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_where_to.*

class whereTo : Fragment() {

    var mail: String? =null
    var password:String?=null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_where_to, container, false)

    }

    @SuppressLint("CommitPrefEdits")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val sharedPreferences: SharedPreferences = this.activity!!.getSharedPreferences("remember_me",Context.MODE_PRIVATE)
         mail=sharedPreferences.getString("email_id",null)
         password=sharedPreferences.getString("login_password",null)
        if (mail == null && password == null)
        {

            btnLogIn.setOnClickListener {

                view.findNavController().navigate(R.id.login2)
            }
            btnSignUp.setOnClickListener {
                view.findNavController().navigate(R.id.signup)
            }

        } else {
         view.findNavController().navigate(R.id.action_whereTo_to_navigation)

        }
    }
}