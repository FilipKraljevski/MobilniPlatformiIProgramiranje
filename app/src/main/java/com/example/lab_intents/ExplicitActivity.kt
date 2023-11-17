package com.example.lab_intents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class ExplicitActivity : AppCompatActivity() {

    private lateinit var editTextChoice: EditText
    private lateinit var btnOkay: Button
    private lateinit var btnCancel: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explicit)

        editTextChoice = findViewById(R.id.editTextChoice)
        btnOkay = findViewById(R.id.btnOkay)
        btnCancel = findViewById(R.id.btnCancel)

        btnOkay.setOnClickListener{
            Intent().let { i ->
                i.putExtra("result", editTextChoice.text.toString())
                setResult(RESULT_OK, i)
                finish()
            }
        }

        btnCancel.setOnClickListener{
            finish()
        }
    }
}