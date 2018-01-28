package com.nikolaykul.android.mvp.comparison.presentation.main

import com.nikolaykul.android.mvp.comparison.domain.Credentials
import com.nikolaykul.android.mvp.comparison.domain.MainInteractor
import com.nikolaykul.android.mvp.comparison.presentation.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

/**
 * Created by nikolay
 */

class MainPresenter @Inject constructor(
        private val interactor: MainInteractor
) : BasePresenter<MainMvpView>() {

    fun login(username: String, password: String) {
        interactor.login(Credentials(username, password))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { view?.showLoading() }
                .doOnTerminate { view?.hideLoading() }
                .subscribe(
                        { view?.showCredentials(username, password) },
                        { print(it) })
    }

}