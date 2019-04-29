package com.intact.filmireview.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjection


/**
 * Created by Anurag Garg on 2019-04-27.
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this@BaseActivity)
        super.onCreate(savedInstanceState)
    }
}