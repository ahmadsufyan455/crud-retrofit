package com.zerodev.crudretrofit.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zerodev.crudretrofit.model.Article
import com.zerodev.crudretrofit.network.ArticleClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArticleViewModel : ViewModel() {
    private val _articles = MutableLiveData<List<Article>>()
    private val _isSubmitSuccess = MutableLiveData<Boolean>()

    fun setArticles() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = ArticleClient.articleService.getArticles()
            if (response.isSuccessful) {
                _articles.postValue(response.body())
            }
        }
    }

    fun getArticles(): LiveData<List<Article>> = _articles

    fun submitArticle(article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = ArticleClient.articleService.submitArticle(article)
            if (response.isSuccessful) {
                _isSubmitSuccess.postValue(true)
            }
        }
    }

    fun updateArticle(id: String, article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = ArticleClient.articleService.updateArticle(id, article)
            if (response.isSuccessful) {
                _isSubmitSuccess.postValue(true)
            }
        }
    }

    fun deleteArticle(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = ArticleClient.articleService.deleteArticle(id)
            if (response.isSuccessful) {
                _isSubmitSuccess.postValue(true)
            }
        }
    }

    fun isSubmitSuccess(): LiveData<Boolean> = _isSubmitSuccess
}