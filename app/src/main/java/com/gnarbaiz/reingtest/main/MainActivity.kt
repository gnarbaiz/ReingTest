package com.gnarbaiz.reingtest.main

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.gnarbaiz.reingtest.R
import com.gnarbaiz.reingtest.main.adapters.ArticleAdapter
import com.gnarbaiz.reingtest.main.adapters.SwipeToDeleteCallback
import com.gnarbaiz.reingtest.networking.responses.Article
import com.gnarbaiz.reingtest.webview.WebViewActivity
import com.google.android.material.snackbar.Snackbar
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    private lateinit var swipeLayout: SwipeRefreshLayout
    @set:Inject
    lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var articleAdapter: ArticleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycle.addObserver(mainActivityViewModel)
        // Getting SwipeContainerLayout
        swipeLayout = findViewById(R.id.swipe_container)
        // Adding Listener
        swipeLayout.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            mainActivityViewModel.requestArticles()
        })

        mainActivityViewModel.getRequestArticles().observe(this, Observer {
            loadAdapter(it.articlesList)
            hideLoader()
        })


        // Scheme colors for animation
        swipeLayout.setColorSchemeColors(
                resources.getColor(android.R.color.holo_blue_bright),
                resources.getColor(android.R.color.holo_green_light),
                resources.getColor(android.R.color.holo_orange_light),
                resources.getColor(android.R.color.holo_red_light)
        )
        enableSwipeToDeleteAndUndo()
    }

    private fun loadList() {
        hideLoader() //TODO("Not yet implemented")
    }

    private fun loadAdapter(articlesList : ArrayList<Article>) {
        articlesRecyclerView.layoutManager = LinearLayoutManager(this)
        articleAdapter = ArticleAdapter(
            this,
            articlesList
        ) { startWebViewActivity(it) }
        articlesRecyclerView.adapter = articleAdapter
    }

    private fun hideLoader() {
        swipeLayout.isRefreshing = false
    }

    private fun startWebViewActivity(articleUrl: String) {
        startActivity(WebViewActivity.newIntent(this, articleUrl))
    }

    private fun enableSwipeToDeleteAndUndo() {
        val swipeToDeleteCallback: SwipeToDeleteCallback = object : SwipeToDeleteCallback(this) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, i: Int) {
                val position = viewHolder.adapterPosition
                val item = articleAdapter.getData()[position]
                articleAdapter.removeItem(position)
                val snackbar: Snackbar = Snackbar
                    .make(
                        swipeLayout,
                        "Item was removed from the list.",
                        Snackbar.LENGTH_LONG
                    )
                snackbar.setAction("UNDO", View.OnClickListener {
                    articleAdapter.restoreItem(item, position)
                    articlesRecyclerView.scrollToPosition(position)
                })
                snackbar.setActionTextColor(Color.YELLOW)
                snackbar.show()
            }
        }
        val itemTouchhelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchhelper.attachToRecyclerView(articlesRecyclerView)
    }

}