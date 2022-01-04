package com.example.planta.view.home.profile.userProfile

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.planta.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class ProfileFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v = inflater.inflate(R.layout.fragment_profile, container, false)
        var auth= Firebase.auth
        var user= auth.currentUser

        val vm : ProfileViewModel by viewModels()

        val supportFragmentManager = activity?.supportFragmentManager?.beginTransaction()

        var unlogg= UnloggedFragment()

        var logg = LoggedFragment()


        vm.checkLogin(user).observe(this,{
            if(it){
                 supportFragmentManager
                    ?.replace(R.id.profileFrameLayout,logg)?.commit()

             }else{
                 supportFragmentManager
                    ?.remove(logg)
                    ?.replace(R.id.profileFrameLayout, unlogg)?.commit()
             }
        })

        return v
    }


}