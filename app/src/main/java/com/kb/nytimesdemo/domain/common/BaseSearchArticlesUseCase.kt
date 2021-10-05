package com.kb.nytimes.foundation.common

import com.kb.nytimes.model.SearchedArticlesResponse

abstract class BaseSearchArticlesUseCase : BaseUseCase<BaseSearchArticlesUseCase.Params, SearchedArticlesResponse>() {
    data class Params(val searchResult: String, val desk: String)
}