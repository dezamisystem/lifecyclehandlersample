package com.dezamisystem.lifecyclehandlersample

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by dezamisystem2 on 2018/06/16.
 * Custom Log Output
 */
class Logtime {

    companion object {
        fun d(text:String) {

            Log.d(getTagString(), getMessage(text))
        }

        fun w(text:String) {

            Log.w(getTagString(), getMessage(text))
        }

        fun e(text:String) {

            Log.e(getTagString(), getMessage(text))
        }

        fun e(text:String,e:Exception) {

            Log.e(getTagString(), getMessage(text), e)
        }

        fun e(text:String,e:Throwable) {

            Log.e(getTagString(), getMessage(text), e)
        }

        private fun getTagString() : String {
            val nowTimestamp = System.currentTimeMillis()
            return getTimeString(nowTimestamp)
        }

        private fun getTimeString(timestamp:Long) : String {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = timestamp
            val date = Date(timestamp)
            val simpleDateFormat = SimpleDateFormat("HH:mm:ss.SSS", Locale.JAPAN)
            return "[" + simpleDateFormat.format(date) + "]"
        }

        private fun getMessage(text:String) : String {
            return getStackTraceName() + " : " + text
        }

        private fun getStackTraceName() : String {
            val stackTrace = Throwable().stackTrace

            var element = stackTrace.first()

            for (item in stackTrace) {
                val name = item.className
                if (name.indexOf(Logtime::class.java.simpleName) == -1) {
                    element = item
                    break
                }
            }

            val classNamePath = element.className
            val classNameSplit = classNamePath.split(".")
            val className = classNameSplit.last()
            val methodName = element.methodName

            return className + "." + methodName
        }
    }
}