package com.example.planta.util

import android.app.Activity
import java.util.*

class LocalizationUtil {

    companion object{


        fun changeLanguage(activity:Activity,newCode:String): Unit {


            var locale =  Locale(newCode);
            Locale.setDefault(locale);
            var resources = activity.getResources();
            var config = resources.getConfiguration();
            config.setLocale(locale);
            resources.updateConfiguration(config, resources.getDisplayMetrics());


           var currentIntent=activity.intent
            activity.finish()
            activity.startActivity(currentIntent)


        }
    }
}