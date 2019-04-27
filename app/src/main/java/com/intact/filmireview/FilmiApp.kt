package com.intact.filmireview

import android.app.Application
import com.intact.filmireview.util.ReleaseLogTree
import timber.log.Timber


/**
 * Created by Anurag Garg on 2019-04-27.
 */
class FilmiApp : Application() {

    override fun onCreate() {
        super.onCreate()

        // Setting up the timber
        if (BuildConfig.DEBUG) {

            // creating the debug mode
            Timber.plant(object : Timber.DebugTree() {
                override fun createStackElementTag(element: StackTraceElement): String? {
                    return String.format(
                        "C:%s:%s",
                        super.createStackElementTag(element),
                        element.lineNumber
                    )
                }
            })
        } else {
            Timber.plant(ReleaseLogTree())
        }
    }
}