package com.kb.nytimes.model

data class Response(
    val docs: List<Doc>,
    val meta: Meta
)