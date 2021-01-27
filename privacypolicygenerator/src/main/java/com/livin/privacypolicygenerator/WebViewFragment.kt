package com.livin.privacypolicygenerator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_web_view.*

private const val HTML_STRING = "html_string"

class WebViewFragment : Fragment() {
    private var htmlString: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            htmlString = it.getString(HTML_STRING)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_web_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        htmlString?.let {
            webView.loadDataWithBaseURL("", it, "text/html", "UTF-8", "")
        }

        // webView.loadUrl("file:///android_asset/sample.html");
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            WebViewFragment().apply {
                arguments = Bundle().apply {
                    putString(HTML_STRING, param1)
                }
            }
    }
}