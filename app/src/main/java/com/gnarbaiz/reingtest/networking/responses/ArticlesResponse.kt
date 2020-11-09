package com.gnarbaiz.reingtest.networking.responses

import com.google.gson.annotations.SerializedName

data class ArticlesResponse (
	@SerializedName("hits") val articlesList : ArrayList<Article>,
	@SerializedName("nbHits") val nbHits : Int,
	@SerializedName("page") val page : Int,
	@SerializedName("nbPages") val nbPages : Int,
	@SerializedName("hitsPerPage") val hitsPerPage : Int,
	@SerializedName("exhaustiveNbHits") val exhaustiveNbHits : Boolean,
	@SerializedName("query") val query : String,
	@SerializedName("params") val params : String,
	@SerializedName("processingTimeMS") val processingTimeMS : Int

)