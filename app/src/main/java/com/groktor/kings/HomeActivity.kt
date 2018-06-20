package com.groktor.kings

import android.accounts.AccountManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import com.groktor.kings.firebase.KingsNotificationChannel
import kotlinx.android.synthetic.main.home_activity.*



class HomeActivity : AppCompatActivity() {

    private val TAG = "Home Activity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        setSupportActionBar(app_bar)

        KingsNotificationChannel(this).createChannel()

        val manager = getSystemService(Context.ACCOUNT_SERVICE) as AccountManager
        val list = manager.accounts

        Log.e(TAG,"list : ${list.size}")

        val sharedPref = this.getSharedPreferences(
                getString(R.string.shared_preferences), Context.MODE_PRIVATE)

        findNavController(R.id.nav_host_fragment).addOnNavigatedListener { controller, destination ->

            if(sharedPref.getString(getString(R.string.username), "").isNullOrBlank()
                    && destination.id != R.id.introSlidesFragment
                    && destination.id != R.id.userNameFragment){

                controller.navigate(R.id.userNameFragment)
                app_bar.visibility = View.GONE
            }

            when (destination.id) {
                R.id.homeFragment -> {

                    app_bar.navigationIcon = null
                    app_bar.menu.findItem(R.id.profile_menu)?.isVisible = true
                    if (sharedPref.getBoolean(getString(R.string.intro_slides), true)) {
                        controller.navigate(R.id.introSlidesFragment)
                        app_bar.visibility = View.GONE
                    }else{
                        app_bar.visibility = View.VISIBLE
                    }
                }

                R.id.profileFragment -> {

                    app_bar.navigationIcon = ContextCompat.getDrawable(this, R.drawable.ic_arrow_back)
                    app_bar.setNavigationOnClickListener{
                        //What to do on back clicked

                        controller.popBackStack()
                    }
                    app_bar.menu.findItem(R.id.profile_menu)?.isVisible = false
                }
                else -> {
                    app_bar.navigationIcon = ContextCompat.getDrawable(this, R.drawable.ic_arrow_back)
                    app_bar.setNavigationOnClickListener{
                        //What to do on back clicked

                       controller.popBackStack()
                    }
                    app_bar.menu.findItem(R.id.profile_menu)?.isVisible = true
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        return when(item?.itemId){
            R.id.profile_menu -> {
                findNavController(R.id.nav_host_fragment).navigate(R.id.profile_action)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp() = findNavController(R.id.nav_host_fragment).navigateUp()

    override fun onBackPressed() {
        super.onBackPressed()

        Log.e("Acticity", "back pressed")
        if (findNavController(R.id.nav_host_fragment).currentDestination.id == R.id.userNameFragment) finish()
        if (findNavController(R.id.nav_host_fragment).currentDestination.id == R.id.introSlidesFragment) finish()
    }
}
