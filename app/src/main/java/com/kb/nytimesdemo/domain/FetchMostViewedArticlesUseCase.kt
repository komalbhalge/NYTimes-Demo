package com.kb.nytimesdemo.domain

import com.kb.choco.util.extensions.flowSingle
import com.kb.nytimes.foundation.common.BaseArticlesUseCase
import com.kb.nytimes.model.ArticlesResponse
import com.kb.nytimesdemo.data.ArticlesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchMostViewedArticlesUseCase @Inject constructor(private val articlesApi: ArticlesApi) :
    BaseArticlesUseCase() {
    override fun onBuild(params: Params): Flow<ArticlesResponse> {
            return flowSingle {
                articlesApi.mostViewedArticles(
                    params.noOfDays
                )
            }
    }
}