package com.dezamisystem.lifecyclehandlersample

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log

/**
 * Created by dezamisystem2 on 2018/06/16.
 *
 */
class ExtraApplication : Application() {

    companion object {
        private var _shared:ExtraApplication = ExtraApplication()
        fun getInstance() : ExtraApplication = _shared
    }

    enum class AppStatus(val rawValue:Int) {
        BACKGROUND(0),
        RETURN_TO_FOREGROUND(1),
        FOREGROUND(2),
    }

    // アプリ状態
    var mAppStatus = AppStatus.FOREGROUND

    /**
     * Construct
     */
    init {
        _shared = this
    }

    /**
     * Override
     */
    override fun onCreate() {
        super.onCreate()

        Logtime.d("onCreate")

        // Life cycle
        registerActivityLifecycleCallbacks(LifecycleHandler(this))
    }

    /**
     * アプリのアクティビティのライフサイクルの管理
     */
    class LifecycleHandler(private val owner:ExtraApplication) : ActivityLifecycleCallbacks {

        private var createdCount = 0

        private var runningCount = 0

        private var alreadyLaunched = false

        override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
            Logtime.d("onActivityCreated")

            createdCount += 1
            if (createdCount == 1) {

                Logtime.d("created == 1")
            }
        }

        override fun onActivityStarted(activity: Activity?) {
            Logtime.d("onActivityStarted")

            runningCount += 1

            Logtime.d("running = " + Integer.toString(runningCount))

            when(runningCount) {
                0 -> {
                    // アプリ外から復帰時
                    // app must be returned from background just now (or first launch)
                    owner.mAppStatus = AppStatus.RETURN_TO_FOREGROUND

                    Logtime.d("RETURN_TO_FOREGROUND")
                }
                1 -> {
                    owner.mAppStatus = AppStatus.FOREGROUND

                    // 初回起動時ならば
                    if (!alreadyLaunched) {
                        alreadyLaunched = true

                        Logtime.d("初回起動時ならば")
                    }

                    Logtime.d("FOREGROUND")
                }
                else -> {
                    // 2 or more running activities,
                    // should be foreground already.
                    owner.mAppStatus = AppStatus.FOREGROUND

                    Logtime.d("running > 2")
                }
            }

        }

        override fun onActivityResumed(activity: Activity?) {
            Logtime.d("onActivityResumed")
        }

        override fun onActivityPaused(activity: Activity?) {
            Logtime.d("onActivityPaused")
        }

        override fun onActivityStopped(activity: Activity?) {
            Logtime.d("onActivityStopped")

            runningCount -= 1

            Logtime.d("running = " + Integer.toString(runningCount))

            if (runningCount <= 0) {
                runningCount = 0
                // no active activity
                // app goes to background
                owner.mAppStatus = AppStatus.BACKGROUND

                Logtime.d("BACKGROUND")
            }
        }

        override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
            Logtime.d("onActivitySaveInstanceState")
        }

        override fun onActivityDestroyed(activity: Activity?) {
            Logtime.d("onActivityDestroyed")
        }

    }
}