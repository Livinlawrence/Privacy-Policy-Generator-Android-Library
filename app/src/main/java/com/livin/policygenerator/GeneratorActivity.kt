package com.livin.policygenerator

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.livin.privacypolicygenerator.PrivacyPolicyDetailsFragment
import com.livin.privacypolicygenerator.WebViewFragment
import com.livin.privacypolicygenerator.interfaces.ActivityCallBack
import kotlinx.android.synthetic.main.activity_generator.*


class GeneratorActivity : AppCompatActivity(), ActivityCallBack {
    private lateinit var mInterstitialAd: InterstitialAd
    private var mHtml: String? = null
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


        mInterstitialAd = InterstitialAd(this)
        mInterstitialAd.adUnitId = "ca-app-pub-3910661356070266/5703917892"
        mInterstitialAd.loadAd(AdRequest.Builder().build())
        //Google Ads
        //Google Ads
        mInterstitialAd.adListener = object : AdListener() {
            override fun onAdClosed() {
                // Code to be executed when the interstitial ad is closed.
                showHtmlWebView()
                mInterstitialAd.loadAd(AdRequest.Builder().build())
            }
        }
    }

    override fun htmlGenerated(htmlString: String) {
        mHtml = htmlString
        onLevelCompleted()
    }

    fun onLevelCompleted() {
        if (mInterstitialAd.isLoaded) {
            mInterstitialAd.show()
        } else {
            showHtmlWebView()
            Log.d("TAG", "The interstitial wasn't loaded yet.")
        }
        //interstitialBuilder.show(this);
    }

    fun showHtmlWebView() {
        mHtml?.let {
            supportActionBar?.title = "Privacy Policy"
            supportFragmentManager.beginTransaction()
                .add(R.id.frameLayout, WebViewFragment.newInstance(it))
                .addToBackStack(WebViewFragment.TAG).commit()
        }
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