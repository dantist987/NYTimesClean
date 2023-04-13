package com.example.nytimesclean.mainPage.ui

import android.util.Log
import com.example.nytimesclean.common.mvp.BasePresenter
import com.example.nytimesclean.mainPage.interactor.MainPageInteractor
import kotlinx.coroutines.launch

class MainPagePresenter(
    private val interactor: MainPageInteractor
) : BasePresenter<MainPageContract.View>(), MainPageContract.Presenter {

    override fun getArticles(page: Int) {
        launch {
            try {
                interactor.loadArticles(page)
            } catch (e: Exception) {
                Log.e("articles", "Error loadinh articles", e)
            }

        }
    }

    override fun attach(view: MainPageContract.View) {
        super.attach(view)
        launch {
            interactor.getAllLocalArticlesFlow()
                .collect() {
                    view.showArticles(it)
                }
        }
    }
}