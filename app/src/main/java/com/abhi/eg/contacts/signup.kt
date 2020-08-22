package com.abhi.eg.contacts

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_signup.*

/**
 * A simple [Fragment] subclass.
 */
class signup : Fragment() {
    //var preferences = this.activity!!.getSharedPreferences("pref", Context.MODE_PRIVATE)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup, container, false)
    }

    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        btnSignupinSignup.setOnClickListener {
            var name: String = etNAMESignup.text.toString().trim()
            var email: String = etEmailSignup.text.toString().trim()
            var password: String = etPasswordSignup.text.toString().trim()
            var confirmpass: String = etConfirmPasswordSignup.text.toString().trim()

            if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty() && !confirmpass.isEmpty()) {

                if (password.equals(confirmpass)) {


                    var bundle = bundleOf("email_address" to email, "pin" to password)

                    view.findNavController().navigate(R.id.login2, bundle)

                } else {

                    Toast.makeText(

                        this.activity,
                        "password and confirm password does not match",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(
                    this.activity,
                    "fielse cannot be emplty",
                    Toast.LENGTH_SHORT
                ).show()

            }
        }
    }
}

