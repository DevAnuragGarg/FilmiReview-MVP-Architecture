package com.intact.filmireview.ui.base


/**
 * Created by Anurag Garg on 2019-04-27.
 */
interface BasePresenter<V : BaseView> {

    fun onAttach(view: V?)

    fun onDetach()

    fun getView(): V?
}