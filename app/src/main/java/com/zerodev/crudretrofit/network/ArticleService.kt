package com.zerodev.crudretrofit.network

import com.zerodev.crudretrofit.model.Article
import retrofit2.Response
import retrofit2.http.*

interface ArticleService {

    @GET("article")
    suspend fun getArticles(): Response<List<Article>>

    @POST("article")
    suspend fun submitArticle(@Body article: Article): Response<Article>

    @PUT("article/{id}")
    suspend fun updateArticle(@Path("id") id: String, @Body article: Article): Response<Article>

    @DELETE("article/{id}")
    suspend fun deleteArticle(@Path("id") id: String): Response<Article>

}