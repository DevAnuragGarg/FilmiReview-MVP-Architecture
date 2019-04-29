package com.intact.filmireview

import android.app.Activity
import android.app.Application
import com.intact.filmireview.di.component.DaggerAppComponent
import com.intact.filmireview.util.ReleaseLogTree
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject


/**
 * Created by Anurag Garg on 2019-04-27.
 */
class FilmiApp : Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        // setting up the dagger
        DaggerAppComponent
            .builder()
            .application(this)
            .build()
            .inject(this)

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

    // injector for the activity
    override fun activityInjector(): AndroidInjector<Activity> = activityInjector
}