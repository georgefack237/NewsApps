package com.example.newsapp

import android.app.Application
import com.example.newsapp.db.ArticleDatabase


class BaseApplication: Application() {

    val database: ArticleDatabase by lazy {
        ArticleDatabase.getDatabase(this)
    }
}