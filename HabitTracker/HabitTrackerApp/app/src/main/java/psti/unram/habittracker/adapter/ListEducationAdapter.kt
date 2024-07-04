package psti.unram.habittracker.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import psti.unram.habittracker.R
import psti.unram.habittracker.model.Article
import psti.unram.habittracker.ui.detail.DetailActivity

class ListEducationAdapter(private val listHero: ArrayList<Article>) : RecyclerView.Adapter<ListEducationAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listHero[position])
    }

    override fun getItemCount(): Int = listHero.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var imgPhoto: ImageView = itemView.findViewById(R.id.iv_image)
        private var tvTitle: TextView = itemView.findViewById(R.id.tv_title)
        private var tvPubDate: TextView = itemView.findViewById(R.id.tv_pubDate)

        @SuppressLint("SetTextI18n")
        fun bind(article: Article) {
            Glide.with(itemView.context)
                .load(article.thumbnail)
                .into(imgPhoto)
            tvTitle.text = article.title
            tvPubDate.text = "publication date : ${article.pubDate}"

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra("Article", article)
                itemView.context.startActivity(intent)
            }
        }
    }
}