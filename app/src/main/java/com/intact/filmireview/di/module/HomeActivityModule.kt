package com.intact.filmireview.di.module

import com.intact.filmireview.ui.home.HomeContract
import com.intact.filmireview.ui.home.HomePresenterImpl
import dagger.Binds
import dagger.Module


/**
 * Created by Anurag Garg on 2019-04-29.
 */
@Module
abstract class HomeActivityModule {

    @Binds
    abstract fun provideHomePresenter(presenter: HomePresenterImpl<HomeContract.HomeView>): HomeContract.HomePresenter<HomeContract.HomeView>
}