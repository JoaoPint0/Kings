package com.groktor.kings.ui.intro

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.groktor.kings.R
import kotlinx.android.synthetic.main.user_name_fragment.*
import kotlinx.android.synthetic.main.user_name_fragment.view.*

class UserNameFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.user_name_fragment, container, false)

        view.save_username.setOnClickListener {

            saveUserName()
        }

        view.save_username.setOnEditorActionListener { _, i, _ ->
            Log.e("UserName" , i.toString())
            if (i ==  EditorInfo.IME_ACTION_DONE){
                saveUserName()
            }
            true
        }

        return view
    }

    private fun saveUserName(){

        when {
            edit_username.text.isNullOrBlank() -> {

                edit_username.error = getString(R.string.text_error_blank)
                return
            }
            edit_username.text!!.length < 4 -> {

                edit_username.error = getString(R.string.text_error_less)
                return
            }
            else -> edit_username.error = null
        }


        val sharedPref = this.activity?.getSharedPreferences(
                getString(R.string.shared_preferences), Context.MODE_PRIVATE)

        sharedPref?.edit()?.putString(getString(R.string.username),edit_username.text.toString())?.apply()

        exitUserNameFragment()
    }

    private fun exitUserNameFragment(){

        findNavController(this).navigate(R.id.exit_intro_action)
    }

}
