package com.gnarbaiz.reingtest.networking.responses

data class StoryTitle(
    val matchLevel: String,
    val matchedWords: List<Any>,
    val value: String
)