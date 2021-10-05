package com.kb.nytimes.model

data class ArticlesResponse(
    val copyright: String,
    val num_results: Int,
    val results: List<Articles>,
    val status: String
)