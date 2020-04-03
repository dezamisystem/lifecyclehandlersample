package com.dezamisystem.lifecyclehandlersample.asynctask

import android.content.Context
import android.os.Handler

import com.dezamisystem.lifecyclehandlersample.Logtime

class TestForAsync {

    companion object {

        fun beginTest(context: Context) {

            Logtime.d("<<< start")

            Logtime.d("+++ task1")
            val task1 = SeriesAsyncTask()
            task1.setOnCallback(object : SeriesAsyncTask.Callback() {
                override fun execute() {
                    Logtime.d("callback : task1")
                }
            }).execute()
            Logtime.d("task1 ---")

            Logtime.d("+++ task2")
            val task2 = SeriesAsyncTask()
            task2.setOnCallback(object : SeriesAsyncTask.Callback() {
                override fun execute() {
                    Logtime.d("callback : task2")
                }
            }).execute()
            Logtime.d("task2 ---")

            Logtime.d("+++ task3")
            val task3 = SeriesAsyncTask()
            task3.setOnCallback(object : SeriesAsyncTask.Callback() {
                override fun execute() {
                    Logtime.d("callback : task3")
                }
            }).execute()
            Logtime.d("task3 ---")

            Logtime.d("+++ task4")
            val task4 = SeriesAsyncTask()
            task4.setOnCallback(object : SeriesAsyncTask.Callback() {
                override fun execute() {
                    Logtime.d("callback : task4")
                }
            }).execute()
            Logtime.d("task4 ---")

            Logtime.d("end >>>")
        }
    }

}
