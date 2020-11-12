package com.gnarbaiz.reingtest.networking.responses

import androidx.room.*
import com.squareup.moshi.JsonClass

@Entity(tableName = "Articles", indices = [Index(value = ["story_id"], unique = true)])
@JsonClass(generateAdapter = true)
data class Article(
    val author: String?,
    val comment_text: String?,
    val created_at: String?,
    val created_at_i: Int?,
    val num_comments: Int?,
    @PrimaryKey
    val objectID: Int,
    val parent_id: Int?,
    val points: Int?,
    val story_id: Int?,
    val story_text: String?,
    val story_title: String?,
    val story_url: String?,
    val title: String?,
    val url: String?,
    var isVisible: Boolean? = true
) {

}