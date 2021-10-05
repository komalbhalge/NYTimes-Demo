package com.kb.nytimes.model

data class SearchedArticlesResponse(
    val copyright: String,
    val response: Response,
    val status: String
)