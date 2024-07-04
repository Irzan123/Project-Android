package psti.unram.habittracker.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import psti.unram.habittracker.R
import psti.unram.habittracker.databinding.ActivityDetailBinding
import psti.unram.habittracker.model.Article

class DetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupData()
    }

    private fun setupData() {
        val model = intent.getParcelableExtra<Article>("Article") as Article
        Glide.with(applicationContext)
            .load(model.thumbnail)
            .into(findViewById(R.id.iv_thumbnail))

        val tvTitle = binding.tvTitle
        val tvDescription = binding.tvDescription
        val tvPublicationDate = binding.tvPubDate

        tvTitle.text = model.title
        tvPublicationDate.text = model.pubDate
        tvDescription.text = model.description
    }
}