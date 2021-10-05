package com.kb.nytimesdemo.ui.dashboard

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kb.choco.ui.common.BaseViewModel
import com.kb.choco.util.extensions.launchWith
import com.kb.nytimes.foundation.common.BaseArticlesUseCase
import com.kb.nytimes.model.ArticlesResponse
import com.kb.nytimesdemo.R
import com.kb.nytimesdemo.domain.FetchMostViewedArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val fetchMostViewedArticlesUseCase: FetchMostViewedArticlesUseCase,
) : BaseViewModel() {

    override val coroutineContext: CoroutineContext
        get() = viewModelScope.coroutineContext


    val articlesList = MutableLiveData<ArticlesResponse>()

    private fun retrieveMostViewedList(noOfDays: Int) {
        val articlesUseCase = fetchMostViewedArticlesUseCase
        articlesUseCase.build(
            BaseArticlesUseCase.Params(noOfDays)
        )
            .onEach { articlesList.postValue(it) }
            .launchWith(this, onError = { printError(it) })
    }

    fun printError(error: Throwable) {
        Log.e("TAG", error.localizedMessage)
        showNoConnectionError()
    }
}