package com.livin.privacypolicygenerator

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.livin.privacypolicygenerator.interfaces.ActivityCallBack
import kotlinx.android.synthetic.main.activity_generator.*

class GeneratorActivity : AppCompatActivity(), ActivityCallBack {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generator)

        setSupportActionBar(toolbar)
        supportActionBar?.title = "Generate Privacy Policy"
        supportActionBar?.setDisplayShowTitleEnabled(true)
        //set back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)


        val type = intent.getIntExtra("item_type", -1)
        supportFragmentManager.beginTransaction()
            .add(R.id.frameLayout, PrivacyPolicyDetailsFragment.newInstance(type)).commit()
    }

    override fun htmlGenerated(htmlString: String) {
        supportActionBar?.title = "Privacy Policy"
        supportFragmentManager.beginTransaction()
            .add(R.id.frameLayout, WebViewFragment.newInstance(htmlString))
            .addToBackStack(WebViewFragment.TAG).commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        when (supportFragmentManager.findFragmentById(R.id.frameLayout)) {
            is PrivacyPolicyDetailsFragment -> {
                supportActionBar?.title = "Generate Privacy Policy"
            }
            is WebViewFragment -> {
                supportActionBar?.title = "Privacy Policy"
            }
        }
    }
}