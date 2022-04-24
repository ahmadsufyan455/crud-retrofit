package com.zerodev.crudretrofit.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.zerodev.crudretrofit.R
import com.zerodev.crudretrofit.databinding.ActivityAddArticleBinding
import com.zerodev.crudretrofit.model.Article
import com.zerodev.crudretrofit.viewmodel.ArticleViewModel

class AddArticleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddArticleBinding
    private lateinit var viewModel: ArticleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = getString(R.string.submit)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[ArticleViewModel::class.java]

        binding.submit.setOnClickListener {
            if (binding.edtTitle.text.toString().isEmpty() || binding.edtDesc.text.toString()
                    .isEmpty()
            ) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                val article = Article(
                    title = binding.edtTitle.text.toString(),
                    description = binding.edtDesc.text.toString()
                )
                binding.progressBar.visibility = View.VISIBLE
                binding.submit.visibility = View.GONE
                viewModel.submitArticle(article)
                viewModel.isSubmitSuccess().observe(this) { isSubmitSuccess ->
                    if (isSubmitSuccess) {
                        startActivity(Intent(this, SuccessSubmitActivity::class.java))
                    }
                }
            }
        }
    }
}