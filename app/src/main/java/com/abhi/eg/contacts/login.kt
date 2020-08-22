package com.abhi.eg.contacts

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_login.*


/**
 * A simple [Fragment] subclass.
 */
class login : Fragment() {
    lateinit var email: String
    lateinit var password: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        email = arguments!!.getString("email_address").toString()
        password = arguments!!.getString("pin").toString()

        val sharedPreferences: SharedPreferences = this.activity!!.getSharedPreferences("remember_me",Context.MODE_PRIVATE)

        var navController = Navigation.findNavController(view)

        btnLoginInLogin.setOnClickListener {
            var mail = etEmailLogin.text.toString().trim()
            var security = etPasswordLogin.text.toString().trim()

             if (mail.equals(email) && security.equals(password)) {
                 val editor:SharedPreferences.Editor =  sharedPreferences.edit()
                 editor.putString("email_id",email)
                 editor.putString("login_password",password)
                 editor.apply()
                 editor.commit()
                    view.findNavController().navigate(R.id.navigation)
                } else
                    Toast.makeText(
                        this.activity,
                        "please enter the valid email id and password",
                        Toast.LENGTH_SHORT
                    ).show()
            }
        }
}



