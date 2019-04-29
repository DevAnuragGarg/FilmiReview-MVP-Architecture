package com.intact.filmireview.ui.home

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.intact.filmireview.R
import com.intact.filmireview.data.model.ErrorDTO
import com.intact.filmireview.data.model.MovieDTO
import com.intact.filmireview.ui.adapter.MoviesListAdapter
import com.intact.filmireview.ui.base.BaseActivity
import timber.log.Timber
import javax.inject.Inject

class HomeActivity : BaseActivity(), HomeContract.HomeView {

    @BindView(R.id.toolbar)
    lateinit var toolBar: Toolbar

    @BindView(R.id.popularRecyclerView)
    lateinit var popularRecyclerView: RecyclerView

    @BindView(R.id.topRatedMoviesRecyclerView)
    lateinit var topRatedMoviesRecyclerView: RecyclerView

    @Inject
    lateinit var popularMoviesListAdapter: MoviesListAdapter

    @Inject
    lateinit var topRatedMoviesListAdapter: MoviesListAdapter

    @Inject
    internal lateinit var presenter: HomeContract.HomePresenter<HomeContract.HomeView>

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

        // set view in presenter
        presenter.onAttach(this@HomeActivity)

        with(popularRecyclerView) {
            layoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = popularMoviesListAdapter
        }

        with(topRatedMoviesRecyclerView) {
            layoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = topRatedMoviesListAdapter
        }

        // get the data
        presenter.getPopularMovies()
        presenter.getTopRatedMovies()
    }

    // updating the popular movies UI
    override fun updatePopularMoviesUI(list: ArrayList<MovieDTO>?, errorDTO: ErrorDTO?) {

        list?.let {
            popularMoviesListAdapter.setMoviesData(it)
            popularMoviesListAdapter.notifyDataSetChanged()
        }

        errorDTO?.let {
            Timber.e("Popular Movies: $errorDTO")
        }
    }

    // updating the top rated movies UI
    override fun updateTopRatedMoviesUI(list: ArrayList<MovieDTO>?, errorDTO: ErrorDTO?) {

        list?.let {
            topRatedMoviesListAdapter.setMoviesData(it)
            topRatedMoviesListAdapter.notifyDataSetChanged()
        }

        errorDTO?.let {
            Timber.e("Top rated Movies: $errorDTO")
        }
    }
}

