package com.groktor.kings.ui.games.create

import android.Manifest
import android.app.Activity
import android.app.DatePickerDialog
import android.app.Dialog
import androidx.lifecycle.ViewModelProviders
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.core.app.ActivityCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.core.content.ContextCompat
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.groktor.kings.R
import kotlinx.android.synthetic.main.game_creation_fragment.*
import kotlinx.android.synthetic.main.game_creation_fragment.view.*
import java.text.SimpleDateFormat
import java.util.*


class CreateGameFragment : Fragment() {

    private val TAG  = "GameCreationFragment"
    private val REQUEST_CODE_IMAGE = 100
    private val MY_PERMISSIONS_REQUEST_READ_STORAGE =  101

    private lateinit var viewModel: CreateGameViewModel
    private var imageUri: Uri? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.game_creation_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.create_event_start_time_txt.setOnFocusChangeListener { it, b ->
            if(b){
                initDatePickerText(it as TextView)
            }
        }

        view.create_event_end_time_txt.setOnFocusChangeListener { it, b ->
            if(b){
                initDatePickerText(it as TextView)
            }
        }

        view.create_event_end_time_txt.setOnEditorActionListener { _, i, _ ->
            Log.e("Action Event" , "$i")
            if (i ==  EditorInfo.IME_ACTION_DONE){
                createEvent()
            }
            true

        }

        view.create_event_add_photo.setOnClickListener {
            // Make sure the app has correct permissions to run
            if(requestPermissionsIfNecessary()) {

                startChoosePhoto()
            }
        }

        view.create_event_btn.setOnClickListener {

            createEvent()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CreateGameViewModel::class.java)
    }

    private fun createEvent(){
        if(isEventNameValid() && isEventStartValid() && isEventEndValid()) {

            val bundle = Bundle()
            bundle.putString("gameId","Created")
            findNavController(this).navigate(R.id.view_game_action, bundle)
        }
    }

    private fun isEventNameValid(): Boolean {
        val text = create_event_name_txt.text
        if ( text != null && text.isNotEmpty()) {

            create_event_name.error = null
            return true
        }

        create_event_name.error = "Can't be Empty"
        return false
    }

    private fun isEventStartValid(): Boolean {
        val text = create_event_start_time_txt.text
        if ( text != null && text.length >= 8 && validStartDate(text)) {

            create_event_start_time.error = null
            return true
        }

        create_event_start_time.error = "Invalid date"
        return false
    }

    private fun validStartDate(text: Editable): Boolean {

        val today = Date()
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
        val startDate = sdf.parse(text.toString())

        return today.before(startDate)
    }


    private fun isEventEndValid(): Boolean {
        val text = create_event_end_time_txt.text
        Log.e("end time", "${text?.length}")
        if ( text != null && text.length >= 8 && validEndDate(text)) {

            create_event_end_time.error = null
            return true
        }

        create_event_end_time.error = "Invalid date"
        return false
    }

    private fun validEndDate(text:Editable): Boolean {

        val today = Date()
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
        val startDate = sdf.parse( create_event_start_time_txt.text.toString() )
        val endDate  = sdf.parse( text.toString() )

        return startDate.after(today) && endDate.after(startDate)
    }


    private fun initDatePickerText(view: TextView){

        val args = Bundle()
        if(!view.text.isEmpty()){
            args.putString("setDate", view.text.toString())
        }

        val datePicker = DatePickerFragment()
        datePicker.setView(view)
        datePicker.arguments = args
        datePicker.show(fragmentManager, "datePicker")
    }

    private fun startChoosePhoto(){
        Log.e("Tag","start picture activity")
        val chooseIntent = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(chooseIntent, REQUEST_CODE_IMAGE)
    }

    private fun requestPermissionsIfNecessary():Boolean {

        if (ContextCompat.checkSelfPermission(context!!, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity as Activity,
                            Manifest.permission.READ_CONTACTS)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(activity as Activity,
                        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                        MY_PERMISSIONS_REQUEST_READ_STORAGE)
            }
            return false
        }
        else{
            return true
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_CODE_IMAGE -> handleImageRequestResult(data!!)
                else -> Log.d(TAG, "Unknown request code.")
            }
        } else {
            Log.e(TAG, String.format("Unexpected Result code %s", resultCode))
        }
    }

    private fun handleImageRequestResult(data: Intent) {
        if (data.clipData != null) {
            imageUri = data.clipData!!.getItemAt(0).uri
        } else if (data.data != null) {
            imageUri = data.data
        }

        if (imageUri == null) {
            Log.e(TAG, "Invalid input image Uri.")
            return
        }

        Glide.with(context!!)
                .load(imageUri)
                .apply(RequestOptions.circleCropTransform())
                .into(game_creation_image)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            MY_PERMISSIONS_REQUEST_READ_STORAGE -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                    startChoosePhoto()
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(context, R.string.set_permissions_in_settings,
                            Toast.LENGTH_LONG).show()
                    create_event_add_photo.isEnabled = false
                }
                return
            }

        // Add other 'when' lines to check for other
        // permissions this app might request.
            else -> {
                // Ignore all other requests.
            }
        }
    }
}

class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

    lateinit var parent:View

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val c = Calendar.getInstance()
        val ( day, month, year)  = arguments?.getString("setDate")?.split("/")?.map { it.toInt() } ?: listOf(c.get(Calendar.DAY_OF_MONTH),c.get(Calendar.MONTH),c.get(Calendar.YEAR))

        // Create a new instance of DatePickerDialog and return it
        return DatePickerDialog(context, this, year, month, day)
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        // Do something with the date chosen by the user

        val date = Date(year-1900,month,day)
        val df = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
        (parent as TextView).text = df.format(date)
    }

    fun setView(view: View){

        parent = view
    }
}
