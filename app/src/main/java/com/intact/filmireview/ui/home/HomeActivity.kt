package com.intact.filmireview.ui.home

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.intact.filmireview.R
import com.intact.filmireview.ui.base.BaseActivity
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import timber.log.Timber
import javax.inject.Inject

class HomeActivity : BaseActivity() {

    @BindView(R.id.toolbar)
    lateinit var toolBar: Toolbar

    @BindView(R.id.popularRecyclerView)
    lateinit var popularRecyclerView: RecyclerView

    @BindView(R.id.topRatedMoviesRecyclerView)
    lateinit var topRatedMoviesRecyclerView: RecyclerView

    @Inject
    lateinit var popularMoviesAdapter: BaseMoviesAdapter

    @Inject
    lateinit var topRatedMoviesAdapter: BaseMoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initializeVariables()
    }

    private fun initializeVariables() {

        // initialize butterknife
        ButterKnife.bind(this)

        // set the action bar
        setSupportActionBar(toolBar)

        // update empty UI
        //updatePopularMoviesUI()
        //updatedTopRatedMoviesUI()
    }

    // updating the popular movies UI
    private fun updatePopularMoviesUI() {
        popularMoviesAdapter.setMoviesData(ArrayList())

        with(popularRecyclerView) {
            layoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = popularMoviesAdapter
            popularMoviesAdapter.notifyDataSetChanged()
        }
    }

    // updating the top rated movies UI
    private fun updatedTopRatedMoviesUI() {
        topRatedMoviesAdapter.setMoviesData(ArrayList())

        with(topRatedMoviesRecyclerView) {
            layoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = topRatedMoviesAdapter
            topRatedMoviesAdapter.notifyDataSetChanged()
        }
    }
}

