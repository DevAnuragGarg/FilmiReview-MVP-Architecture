package com.intact.filmireview.ui.base

import com.intact.filmireview.data.BaseDataManager
import com.intact.filmireview.util.scheduler.BaseSchedulerProvider
import io.reactivex.disposables.CompositeDisposable


/**
 * Created by Anurag Garg on 2019-04-29.
 */
abstract class BasePresenterImpl<V : BaseView>(
    protected val baseDataManager: BaseDataManager,
    protected val schedulerProvider: BaseSchedulerProvider,
    protected val compositeDisposable: CompositeDisposable
) :
    BasePresenter<V> {

    private var view: V? = null
    private val isViewAttached: Boolean get() = view != null

    override fun onAttach(view: V?) {
        this.view = view
    }

    override fun getView(): V? = view

    override fun onDetach() {
        compositeDisposable.dispose()
        view = null
    }
}