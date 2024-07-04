package psti.unram.habittracker.ui.education

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject
import psti.unram.habittracker.adapter.ListEducationAdapter
import psti.unram.habittracker.databinding.FragmentEducationBinding
import psti.unram.habittracker.model.Article

class EducationFragment : Fragment() {

    companion object{
        private val TAG = EducationFragment::class.java.simpleName
    }

    private var _binding: FragmentEducationBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEducationBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(context)
        binding.rvArticle.setLayoutManager(layoutManager)
        val itemDecoration = DividerItemDecoration(context, layoutManager.orientation)
        binding.rvArticle.addItemDecoration(itemDecoration)

        getListArticle()
    }

    private fun getListArticle(){
        binding.progressBar.visibility = View.VISIBLE
        val client = AsyncHttpClient()
        val url = "https://api-berita-indonesia.vercel.app/antara/lifestyle/"
        client.get(url, object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<Header>,
                responseBody: ByteArray
            ) {
                val listArticle = ArrayList<Article>()
                val result = String(responseBody)
                Log.d(TAG, result)
                try{
                    val jsonObj = JSONObject(result)
                    val objData = jsonObj.getJSONObject("data")
                    val arrayPosts = objData.getJSONArray("posts")
                    Log.d(TAG, objData.toString())
                    Log.d(TAG, arrayPosts.toString())
                    for(i in 0 until arrayPosts.length()){
                        val articleObject = arrayPosts.getJSONObject(i)
                        val title = articleObject.getString("title")
                        val pubDate = articleObject.getString("pubDate")
                        val description = articleObject.getString("description")
                        val thumbnail = articleObject.getString("thumbnail")
                        listArticle.add(Article(title, pubDate, description, thumbnail))
                    }
                    val adapter = ListEducationAdapter(listArticle)
                    binding.rvArticle.adapter = adapter
                }catch (e : Exception){
                    Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
                binding.progressBar.visibility = View.INVISIBLE
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<Header>,
                responseBody: ByteArray,
                error: Throwable
            ) {
                binding.progressBar.visibility = View.INVISIBLE
                val errorMessage = when (statusCode) {
                    401 -> "$statusCode : Bad Request"
                    403 -> "$statusCode : Forbidden"
                    404 -> "$statusCode : Not Found"
                    else -> "$statusCode : ${error.message}"
                }
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }
}