package com.example.lab_intents

import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ImplicitActivity : AppCompatActivity() {

    private lateinit var textViewListActivity: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_implicit)

        textViewListActivity = findViewById(R.id.textViewListActivities)

        textViewListActivity.text = listActivities()
    }

    fun listActivities(): String{
        var intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_LAUNCHER)
        var activities = packageManager.queryIntentActivities(intent, 0)
        var tmp = ""
        for (activity in activities){
            tmp += activity.activityInfo.name + "\n"
        }
        return tmp
    }
}