package com.adnahcodes.customviewapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            setContentView(R.layout.activity_second)
        } catch (e: Exception){
            Log.e(Companion.TAG, "onCreate: $e")
            throw e
        }
    }

    companion object {
        private const val TAG = "SecondActivity"
    }
}