package com.example.planta.util

import android.content.Context
import androidx.appcompat.app.AppCompatActivity

class SharedPreferencesHelper {

    companion object {
        var prefname = "userId"
        var orderPref = "orderId"
        var wishPref  ="wishId"
        var addressPref="address"
        var languagePref="language"

        fun saveUserId(context: Context, uid: String): Unit {
            var prefs = context.getSharedPreferences(prefname, Context.MODE_PRIVATE)
            prefs.edit()
                .putString("id", uid)
                .commit()
        }

        fun getUserId(context: Context): String {
            var prefs = context.getSharedPreferences(prefname, Context.MODE_PRIVATE)
            var id = prefs.getString("id","null")
            return id!!
        }

        fun saveOrderId(context: Context, oid: String): Unit {
            var prefs = context.getSharedPreferences(orderPref, Context.MODE_PRIVATE)
            prefs.edit()
                .putString("order", oid)
                .commit()
        }

        fun getOrderId(context: Context): String {
            var prefs = context.getSharedPreferences(orderPref, Context.MODE_PRIVATE)
            var oid = prefs.getString("order","null")
            return oid!!
        }


        fun saveWishId(context: Context,wid:String){
            var prefs = context.getSharedPreferences(wishPref, Context.MODE_PRIVATE)
            prefs.edit()
                .putString("wish", wid)
                .commit()
        }

        fun getWishListId(context: Context):String{
            var prefs = context.getSharedPreferences(wishPref, Context.MODE_PRIVATE)
            var wid = prefs.getString("wish","null")
            return wid!!
        }

        fun saveAddress(context: Context,address:String){
            var prefs=context.getSharedPreferences(addressPref,Context.MODE_PRIVATE)
            prefs.edit()
                .putString("address",address)
                .commit()
        }

        fun getAddress(context: Context):String{
            var prefs = context.getSharedPreferences(addressPref, Context.MODE_PRIVATE)
            var address = prefs.getString("address","null")
            return address!!
        }

        fun saveLanguage(context: Context,current:String){
            var prefs=context.getSharedPreferences(languagePref,Context.MODE_PRIVATE)
            prefs.edit()
                .putString("language",current)
                .commit()
        }

        fun getLanguage(context: Context):String{
            var prefs=context.getSharedPreferences(languagePref,Context.MODE_PRIVATE)
            var language = prefs.getString("language","null")
            return language!!
        }




    }
}