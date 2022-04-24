package com.zerodev.crudretrofit.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.zerodev.crudretrofit.adapter.ArticleAdapter
import com.zerodev.crudretrofit.databinding.ActivityMainBinding
import com.zerodev.crudretrofit.viewmodel.ArticleViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var articleAdapter: ArticleAdapter
    private lateinit var viewModel: ArticleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[ArticleViewModel::class.java]
        articleAdapter = ArticleAdapter()

        getDataFromApi()
        showData()

        binding.refresh.setOnRefreshListener {
            binding.refresh.isRefreshing = false
            getDataFromApi()
        }

        binding.fab.setOnClickListener {
            startActivity(Intent(this, AddArticleActivity::class.java))
        }
    }

    private fun getDataFromApi() {
        viewModel.setArticles()
        viewModel.getArticles().observe(this) { articles ->
            binding.progressBar.visibility = View.GONE
            if (articles != null) {
                articleAdapter.setArticles(articles)
            }
        }
    }

    private fun showData() {
        with(binding.recycleView) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = articleAdapter
        }
    }
}