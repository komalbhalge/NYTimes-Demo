package com.kb.nytimes.foundation.common

import com.kb.nytimes.model.ArticlesResponse

abstract class BaseArticlesUseCase : BaseUseCase<BaseArticlesUseCase.Params, ArticlesResponse>() {
    data class Params(val noOfDays: Int)
}