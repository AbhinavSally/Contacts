package com.abhi.eg.contacts

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_edit_contact.*
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 */
class EditContact : BaseFragment() {

    private var contact: Contact? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_contact, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

/*        ivEditImage.setOnClickListener {
            val bottomSheet = ModalBottomSheet()
            @Suppress("DEPRECATION")
            bottomSheet.show(fragmentManager!!, "exampleBottomSheet")
        }
*/
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.let {
            contact = EditContactArgs.fromBundle(it).contact
            etEDITNAME.setText(contact?.name)
            etEDITNUMBER.setText(contact?.phoneNumber)
            Glide.with(view!!).load(contact?.image).into(ivProfileEdit)

        }

        btnSaveChange.setOnClickListener {
            val updateName = etEDITNAME.text.toString().trim()
            val updateNumber = etEDITNUMBER.text.toString().trim()
            val profilePic:String = ""

            if (updateName.isEmpty()) {
                etEDITNAME.error = "Name Required"
                etEDITNAME.requestFocus()
                return@setOnClickListener
            }
            if (updateNumber.isEmpty()) {
                etEDITNUMBER.error = "Number Required"
                etEDITNUMBER.requestFocus()
                return@setOnClickListener
            }


            launch {

                context?.let {
                    val mContact = Contact(updateName, updateNumber,profilePic)


                    mContact.id = contact!!.id
                    AppDatabase(it).getContactDao().updateContact(mContact)
            //        it.toast("Contact Updated")


                    //after click update get back to the recycler fragment
                    view!!.findNavController()
                        .navigate(R.id.action_editContact_to_recyclerView)

                }
            }

        }

        btnDeleteChange.setOnClickListener {
            AlertDialog.Builder(context).apply {
                setTitle("Are you sure you want to delete this contact")
                setMessage("You can't undo this operation")
                setPositiveButton("yes"){ _  , _ ->
                    launch {
                        AppDatabase(context).getContactDao().delete(contact!!)
                        val action = EditContactDirections.actionEditContactToRecyclerView()
                        Navigation.findNavController(view!!).navigate(action)
                    }
                }
                setNeutralButton("no"){ _ , _ ->

                }
            }.create().show()

        }


    }
}
