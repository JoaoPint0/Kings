package com.groktor.kings.ui.profile

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.groktor.kings.Injection
import com.groktor.kings.R
import com.groktor.kings.model.Account
import com.groktor.kings.model.Battle
import kotlinx.android.synthetic.main.user_profile_fragment.*

class UserProfileFragment : Fragment() {

    private lateinit var viewModel: UserProfileViewModel
    private val awardsAdapter = AwardsAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.user_profile_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, Injection.provideUserProfileViewModelFactory(context!!))
                .get(UserProfileViewModel::class.java)

        val sharedPref = context!!.getSharedPreferences(
                getString(R.string.shared_preferences), Context.MODE_PRIVATE)

        profile_username.text = sharedPref.getString(getString(R.string.username), "")

        viewModel.searchAccount().observe(this, Observer<Account> {

            Log.e("ProfileFragment", "id ${it?._id}")
            Log.e("ProfileFragment", "photo ${it?.profilePicUrl}")

            Glide.with(context!!)
                    .load(it?.profilePicUrl)
                    .apply(RequestOptions.circleCropTransform())
                    .into(profile_avatar)
        })

        profile_award_list.apply {
            this.adapter = awardsAdapter
        }

        viewModel.battles.observe(this, Observer<PagedList<Battle>> {
            Log.d("Activity", "list: ${it?.size}")
            //showEmptyList(it?.size == 0)
            awardsAdapter.submitList(it)
        })

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}
