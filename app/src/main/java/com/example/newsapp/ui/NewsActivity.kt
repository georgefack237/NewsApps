package com.example.newsapp.ui
import com.example.newsapp.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.newsapp.BaseApplication
import com.example.newsapp.databinding.ActivityNewsBinding
import com.example.newsapp.repository.NewsRepository


class NewsActivity : AppCompatActivity() {

    lateinit var viewModel: NewsViewModel
    private lateinit var binding: ActivityNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val newsRepository = NewsRepository((applicationContext as BaseApplication).database.articleDao())
        val viewModelProviderFactory = NewsViewModelProviderFactory(newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory)[NewsViewModel::class.java]


        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragment: View? = findViewById(R.id.newsNavHostFragment)
        binding.flFragment


        binding.bottomNavigationView.setupWithNavController(fragment!!.findNavController())
    }
}
