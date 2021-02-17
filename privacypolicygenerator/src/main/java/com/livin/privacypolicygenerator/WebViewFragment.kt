package com.livin.privacypolicygenerator

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.livin.privacypolicygenerator.Utils.shareFile
import kotlinx.android.synthetic.main.fragment_web_view.*
import kotlinx.coroutines.*
import java.io.File

private const val HTML_STRING = "html_string"

class WebViewFragment : Fragment() {
    private var htmlString: String? = null
    val job = Job()
    val uiScope = CoroutineScope(Dispatchers.Main + job)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            htmlString = it.getString(HTML_STRING)
        }
        setHasOptionsMenu(true)
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
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.webview_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_share) {
            uiScope.launch(Dispatchers.Main) {
                val file = saveTextFile()
                shareFile(file, requireContext())
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private suspend fun saveTextFile(): File {
        return withContext(Dispatchers.IO) {
            val file =
                File(requireContext().filesDir, System.currentTimeMillis().toString() + ".html")
            try {

                file.printWriter().use { out ->
                    out.println(htmlString)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            file
        }
    }

    companion object {
        const val TAG = "WebViewFragment"

        @JvmStatic
        fun newInstance(param1: String) =
            WebViewFragment().apply {
                arguments = Bundle().apply {
                    putString(HTML_STRING, param1)
                }
            }
    }
}