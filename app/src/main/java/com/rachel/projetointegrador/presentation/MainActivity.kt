package com.rachel.projetointegrador.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.rachel.projetointegrador.R
import com.rachel.projetointegrador.presentation.adapter.FragmentAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val viewpager = findViewById<ViewPager>(R.id.viewpager)
        viewpager.adapter = FragmentAdapter(supportFragmentManager)
        val tablayout = findViewById<TabLayout>(R.id.tablayout)
        tablayout.setupWithViewPager(viewpager)

    }
}