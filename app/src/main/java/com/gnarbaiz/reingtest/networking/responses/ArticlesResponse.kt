package com.gnarbaiz.reingtest.networking.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArticlesResponse (
	@field:Json(name ="hits") val articlesList : List<Article>,
	@field:Json(name ="nbHits") val nbHits : Int,
	@field:Json(name ="page") val page : Int,
	@field:Json(name ="nbPages") val nbPages : Int,
	@field:Json(name ="hitsPerPage") val hitsPerPage : Int,
	@field:Json(name ="exhaustiveNbHits") val exhaustiveNbHits : Boolean,
	@field:Json(name ="query") val query : String,
	@field:Json(name ="params") val params : String,
	@field:Json(name ="processingTimeMS") val processingTimeMS : Int
)