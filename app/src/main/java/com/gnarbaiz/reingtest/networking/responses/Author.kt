package com.gnarbaiz.reingtest.networking.responses

data class Author(
    val matchLevel: String,
    val matchedWords: List<Any>,
    val value: String
)