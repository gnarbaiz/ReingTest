package com.gnarbaiz.reingtest.networking.responses

data class StoryUrl(
    val matchLevel: String,
    val matchedWords: List<Any>,
    val value: String
)