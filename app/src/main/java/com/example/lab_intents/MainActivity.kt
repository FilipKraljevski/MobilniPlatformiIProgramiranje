package com.example.lab_intents

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.ext.SdkExtensions
import android.provider.MediaStore
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    private lateinit var textViewResultExplicitActivity: TextView
    private lateinit var btnExplicitActivity: Button
    private lateinit var btnImplicitActivity: Button
    private lateinit var btnSelectImageActivity: Button

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result ->
        if(result.resultCode == RESULT_OK){
            val data: Intent? = result.data
            textViewResultExplicitActivity.text = data?.getStringExtra("result")
        }
    }

    private val imageLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {result ->
        if(result.resultCode == RESULT_OK) {
            Intent(Intent.ACTION_VIEW).let { i ->
                var uri = result.data?.data
                i.setDataAndType(uri, "image/*")
                startActivity(i)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewResultExplicitActivity = findViewById(R.id.textViewResultExplicitActivity)
        btnExplicitActivity = findViewById(R.id.btnExplicitActivity)
        btnImplicitActivity = findViewById(R.id.btnImplicitActivity)
        btnSelectImageActivity = findViewById(R.id.btnImageSelectActivity)

        btnExplicitActivity.setOnClickListener{
            Intent(this, ExplicitActivity::class.java).let { i ->
                resultLauncher.launch(i)
            }
        }

        btnImplicitActivity.setOnClickListener {
            Intent().apply {
                action = "mk.ukim.finki.mpip.IMPLICIT_ACTION"
            }.let { i ->
                startActivity(i)
            }
        }

        btnSelectImageActivity.setOnClickListener{
            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI).let { i ->
                imageLauncher.launch(i)
            }
        }
    }
}