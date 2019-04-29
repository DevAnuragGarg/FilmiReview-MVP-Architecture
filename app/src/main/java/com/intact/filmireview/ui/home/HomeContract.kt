package com.intact.filmireview.ui.home

import com.intact.filmireview.data.model.ErrorDTO
import com.intact.filmireview.data.model.MovieDTO
import com.intact.filmireview.ui.base.BasePresenter
import com.intact.filmireview.ui.base.BaseView


/**
 * Created by Anurag Garg on 2019-04-29.
 */

interface HomeContract {

    interface HomePresenter<V : HomeView> : BasePresenter<V> {

        fun getPopularMovies()

        fun getTopRatedMovies()
    }

    interface HomeView : BaseView {

        fun updatePopularMoviesUI(list: ArrayList<MovieDTO>?, errorDTO: ErrorDTO?)

        fun updateTopRatedMoviesUI(list: ArrayList<MovieDTO>?, errorDTO: ErrorDTO?)
    }
}