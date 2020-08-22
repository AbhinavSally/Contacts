package com.abhi.eg.contacts

import android.app.Activity
import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_add_contact.*
import kotlinx.coroutines.launch
import java.io.File
import java.lang.Exception

class  AddContact : BaseFragment() {

    val APP_TAG = "MyCustomApp"
    val CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1034
    var photoFileName = "photo"+ System.currentTimeMillis() +".jpg"
    var photoFile: File? = null
    lateinit var uri:Uri

    val PICK_PHOTO_CODE = 1046


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_contact, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        facADD.setOnClickListener {
            selectImage()
        }

        btnSave.setOnClickListener {
            val addName = etADDNAME.text.toString().trim()
            val addNumber = etADDNUMBER.text.toString().trim()
            val image = photoFile.toString()
            //      val profilePic = ivAddImage.

            if (addName.isEmpty()) {
                etADDNAME.error = "Name Required"
                etADDNAME.requestFocus()
                return@setOnClickListener
            }
            if (addNumber.isEmpty()) {
                etADDNUMBER.error = "Number Required"
                etADDNUMBER.requestFocus()
                return@setOnClickListener
            }



            launch {
                val contact = Contact(addName, addNumber,image)
                context?.let {
                    AppDatabase(it).getContactDao().addContact(contact)
                    Toast.makeText(activity,"Contact Saved", Toast.LENGTH_SHORT).show()

                    view!!.findNavController()
                        .navigate(R.id.action_addContact_to_recyclerView)

                }
            }
            hideKeyboard(activity!!)
        }

    }
    private fun selectImage() {
        val options =
            arrayOf<CharSequence>("Take Photo", "Choose from Gallery", "Cancel")
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setTitle("Choose your profile picture")
        builder.setItems(options, DialogInterface.OnClickListener { dialog, item ->
            if (options[item] == "Take Photo") {
                onLaunchCamera(view)
            } else if (options[item] == "Choose from Gallery") {
                onPickPhoto(view)
            } else if (options[item] == "Cancel") {
                dialog.dismiss()
            }
        })
        builder.show()
    }

    fun onPickPhoto(view: View?) {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        if (intent.resolveActivity(activity!!.packageManager) != null) {
            startActivityForResult(intent, PICK_PHOTO_CODE)
        }
    }

    fun onLaunchCamera(view: View?) {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        photoFile = getPhotoFileUri(photoFileName)
        val fileProvider: Uri =
            FileProvider.getUriForFile(activity!!, "com.codepath.fileprovider1", photoFile!!)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider)
        if (intent.resolveActivity(activity!!.packageManager) != null) {
            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE)
        }
    }

    fun getPhotoFileUri(fileName: String): File? {
        val mediaStorageDir =
            File(getActivity()!!.getExternalFilesDir(Environment.DIRECTORY_PICTURES), APP_TAG)
        if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()) {
            Log.d(APP_TAG, "failed to create directory")
        }
        return File(mediaStorageDir.path + File.separator + fileName)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                val takenImage = BitmapFactory.decodeFile(photoFile!!.absolutePath)
                val ivPreview: ImageView = view!!.findViewById(R.id.ivProfileAdd) as ImageView
                ivPreview.setImageBitmap(takenImage)
                if (data != null) {
                    uri= data.data!!
                    try {
                     var bitmap=MediaStore.Images.Media.getBitmap(activity!!.application.contentResolver,uri)
                    }catch (e:Exception)
                    {}
                }
            } else {
                Toast.makeText(activity, "Picture wasn't taken!", Toast.LENGTH_SHORT).show()
            }
        }
    }




    fun hideKeyboard(activity: Activity) {
        val inputManager: InputMethodManager = activity
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val currentFocusedView = activity.currentFocus
        if (currentFocusedView != null) {
            inputManager.hideSoftInputFromWindow(
                currentFocusedView.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }
}

