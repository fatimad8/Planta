package com.example.planta.view.home.profile.userProfile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.planta.R
import com.example.planta.util.SharedPreferencesHelper
import com.example.planta.view.home.mainScreen.MainActivity
import com.example.planta.view.home.profile.orderHistory.OrderHistoryFragment
import com.example.planta.view.home.profile.wishList.WishlistFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class LoggedFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v= inflater.inflate(R.layout.fragment_logged, container, false)
        var bottomNavigationView=v.findViewById<BottomNavigationView>(R.id.bottom_navigation)
        var logout=v.findViewById<ImageView>(R.id.imageViewLogout)
        var shortName=v.findViewById<TextView>(R.id.textViewShortName)
        var name= v.findViewById<TextView>(R.id.textViewUserName)
        var email=v.findViewById<TextView>(R.id.textViewUserEmail)

        //val supportFragmentManager = activity?.supportFragmentManager?.beginTransaction()


        shortName.text

        val db =Firebase.firestore
        val currentUser=Firebase.auth.currentUser

        db.collection("users").document(currentUser!!.uid).addSnapshotListener(){result,e ->
            var username= result?.getString("fullname")!!
            name.text=username
            email.text=result.getString("email")
            var listname=username.split(" ")
            if(listname.size>=2)
                shortName.text=(listname[0].first()+""+listname[1]?.first()).toUpperCase()
            else if(listname.size==1)
                shortName.text=(listname[0].first()+"").toUpperCase()
        }

        logout.setOnClickListener {
            Firebase.auth.signOut()

            Toast.makeText(context, getString(R.string.logout), Toast.LENGTH_SHORT).show()
            context?.startActivity(Intent(context,MainActivity::class.java))
        }

        val supportFragmentManager = activity?.supportFragmentManager?.beginTransaction()
        supportFragmentManager?.replace(R.id.WframeLayout,WishlistFragment())?.commit()

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.wishlist -> {
                    val supportFragmentManager = activity?.supportFragmentManager?.beginTransaction()
                    supportFragmentManager?.replace(R.id.WframeLayout,WishlistFragment())?.commit()
                    true
                }
                R.id.oHistory -> {
                    val supportFragmentManager = activity?.supportFragmentManager?.beginTransaction()
                    supportFragmentManager?.replace(R.id.WframeLayout,OrderHistoryFragment())?.commit()
                    true
                }
                else -> true
            }
          }



        return v
    }


}