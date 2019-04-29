package com.intact.filmireview.ui.home

import com.intact.filmireview.data.BaseDataManager
import com.intact.filmireview.data.model.ErrorDTO
import com.intact.filmireview.ui.base.BasePresenterImpl
import com.intact.filmireview.util.scheduler.BaseSchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject


/**
 * Base presenter impl is being extended because
 * it will help not to implement base functions
 * in every presenter impl. Whereas home contract
 * presenter will specify the need of the functions
 * especially for home presenter which is to be
 * implement here
 *
 * Created by Anurag Garg on 2019-04-29.
 */
class HomePresenterImpl<V : HomeContract.HomeView> @Inject constructor(
    baseDataManager: BaseDataManager,
    schedulerProvider: BaseSchedulerProvider,
    compositeDisposable: CompositeDisposable
) :
    BasePresenterImpl<V>(
        schedulerProvider = schedulerProvider,
        compositeDisposable = compositeDisposable,
        baseDataManager = baseDataManager
    ),
    HomeContract.HomePresenter<V> {

    override fun getPopularMovies() {
        compositeDisposable.add(
            baseDataManager.getPopularMovies("1")
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({ it ->
                    Timber.d("Success: Popular movies response received: ${it.movies}")
                    getView()?.updatePopularMoviesUI(it.movies, null)
                }, {
                    val error = it as HttpException
                    Timber.d("ErrorCode: ${error.code()} & Failure: ${error.localizedMessage}")
                    getView()?.updatePopularMoviesUI(
                        null,
                        ErrorDTO(code = error.code(), message = error.localizedMessage)
                    )
                })
        )
    }

    override fun getTopRatedMovies() {
        compositeDisposable.add(
            baseDataManager.getTopRatedMovies("1")
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({ it ->
                    Timber.d("Success: Top rated movies response received: ${it.movies}")
                    getView()?.updateTopRatedMoviesUI(it.movies, null)
                }, {
                    val error = it as HttpException
                    Timber.d("ErrorCode: ${error.code()} & Failure: ${error.localizedMessage}")
                    getView()?.updateTopRatedMoviesUI(
                        null,
                        ErrorDTO(code = error.code(), message = error.localizedMessage)
                    )
                })
        )
    }
}