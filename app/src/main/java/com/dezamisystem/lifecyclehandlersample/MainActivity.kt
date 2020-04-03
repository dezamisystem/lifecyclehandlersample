package com.dezamisystem.lifecyclehandlersample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.dezamisystem.lifecyclehandlersample.asynctask.TestForAsync

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onStart() {
        super.onStart()

        TestForAsync.beginTest(this)
    }
}
