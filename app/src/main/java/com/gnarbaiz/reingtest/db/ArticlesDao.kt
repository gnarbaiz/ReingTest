package com.gnarbaiz.reingtest.db

import androidx.room.*
import com.gnarbaiz.reingtest.networking.responses.Article

@Dao
interface ArticlesDao {

    @Query("SELECT * FROM Articles")
    fun getAll(): List<Article>

    @Query("SELECT * FROM Articles WHERE isVisible = 1")
    fun getAllVisible(): List<Article>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(article: Article)

    @Delete
    fun delete(article: Article)

    /**
     * Updating only deteletion state
     * By order id
     */
    @Query("UPDATE Articles SET isVisible=:isVisible WHERE objectID = :objectID")
    fun update(isVisible: Boolean?, objectID: Int)

    @Query("SELECT * FROM Articles WHERE objectID == :objectID ")
    fun getArticleById(objectID: Int) : Article
}