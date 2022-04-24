package com.zerodev.crudretrofit.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.zerodev.crudretrofit.R
import com.zerodev.crudretrofit.databinding.ActivityUpdateArticleBinding
import com.zerodev.crudretrofit.model.Article
import com.zerodev.crudretrofit.viewmodel.ArticleViewModel

class UpdateArticleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateArticleBinding
    private lateinit var viewModel: ArticleViewModel
    private lateinit var articleId: String

    companion object {
        const val EXTRA_ID = "extra_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = getString(R.string.update)

        val articleData = intent.getParcelableExtra<Article>(EXTRA_ID)
        articleId = articleData?.id.toString()

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[ArticleViewModel::class.java]

        binding.edtTitle.setText(articleData?.title)
        binding.edtDesc.setText(articleData?.description)

        binding.update.setOnClickListener {
            val article = Article(
                title = binding.edtTitle.text.toString(),
                description = binding.edtDesc.text.toString()
            )
            binding.progressBar.visibility = View.VISIBLE
            binding.update.visibility = View.GONE
            viewModel.updateArticle(articleData?.id.toString(), article)
            viewModel.isSubmitSuccess().observe(this) { isSubmitSuccess ->
                if (isSubmitSuccess) {
                    startActivity(Intent(this, SuccessSubmitActivity::class.java))
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.update_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete) {
            val builder = AlertDialog.Builder(this)
            builder.setPositiveButton("Yes") { _, _ ->
                viewModel.deleteArticle(articleId)
            }
            builder.setNegativeButton("No") { _, _ -> }
            builder.setTitle("Delete article")
            builder.setMessage("Are you sure want to delete this article ?")
            builder.create().show()

            viewModel.isSubmitSuccess().observe(this) { isSuccess ->
                if (isSuccess) {
                    Toast.makeText(this, "Article successfully deleted!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
                    finishAffinity()
                } else {
                    Toast.makeText(this, "Article failed to deleted!", Toast.LENGTH_SHORT).show()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}