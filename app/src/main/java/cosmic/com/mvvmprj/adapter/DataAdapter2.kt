package cosmic.com.mvvmprj.adapter
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import cosmic.com.mvvmprj.Github.GithubOwner
import cosmic.com.mvvmprj.R
import cosmic.com.mvvmprj.model.DbHelper

class DataAdapter2 : RecyclerView.Adapter<DataAdapter2.DataViewHolder> {

    internal lateinit var context: Context
    internal lateinit var dataList: List<GithubOwner>
    internal lateinit var owner: GithubOwner
    internal var isLike: Boolean = false
    internal lateinit var recyclerView:RecyclerView

    constructor(context: Context, dataList: List<GithubOwner>) {
        this.context = context
        this.dataList = dataList
    }

    constructor()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false)
        return DataViewHolder(view)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {

        owner = dataList[position]

        val imageUri = Uri.parse(owner.avatar_url)
        holder.tv_name.text = owner.login
        val html = owner.html_url
        holder.tv_html.text = html

        val target = holder.imageView
        Glide.with(context)
            .load(imageUri)
            .fitCenter()
            .centerCrop()
            .override(100, 100)
            .into(target)

        holder.saveBtn.setOnClickListener {
            cancleLike(owner.login)
            notifyDataSetChanged()
        }
    }

    private fun cancleLike(name: String) {
        val dbHelper = DbHelper(context, "HUB2.db", null, 1)
        dbHelper.delete(name)
        Toast.makeText(context,"좋아요 취소", Toast.LENGTH_SHORT).show()
        refresh()
    }

    private fun saveLike(name: String, url: String, html: String, etc: Float, id:Int) {
        val dbHelper = DbHelper(context, "HUB2.db", null, 1)
        dbHelper.insert(name, url, html, etc, id)
        Toast.makeText(context,"좋아요", Toast.LENGTH_SHORT).show()
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun refresh(){
        val adapter=DataAdapter2(context,dataList)
        adapter.notifyDataSetChanged()
    }

    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var tv_name: TextView
        internal var tv_score: TextView
        internal var tv_html: TextView
        internal var imageView: ImageView
        internal var saveBtn: ImageButton

        init {
            this.tv_name = itemView.findViewById(R.id.repo_name)
            this.imageView = itemView.findViewById(R.id.repo_image)
            this.tv_score = itemView.findViewById(R.id.repo_score)
            this.saveBtn = itemView.findViewById(R.id.saveBtn)
            this.tv_html = itemView.findViewById(R.id.repo_html)

            saveBtn.setBackgroundResource(R.drawable.baseline_favorite_black_18dp)
        }


    }
}
