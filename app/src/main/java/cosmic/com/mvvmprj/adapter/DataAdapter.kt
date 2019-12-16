package cosmic.com.mvvmprj.adapter

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import cosmic.com.mvvmprj.Github.GitHubResult
import cosmic.com.mvvmprj.R
import cosmic.com.mvvmprj.model.DbHelper
import cosmic.com.mvvmprj.model.User

class DataAdapter : RecyclerView.Adapter<DataAdapter.DataViewHolder> {

    internal lateinit var context: Context
    internal lateinit var gitHubResult: GitHubResult
    internal lateinit var user: User
    internal lateinit var userName: String
    internal var isLike: Boolean = false

    constructor(context: Context?, gitHubResult: GitHubResult, name: String) {
        this.context = context!!
        this.gitHubResult=gitHubResult;
        this.userName = name
    }

    constructor(context: Context, gitHubResult: GitHubResult) {
        this.context = context
        this.gitHubResult = gitHubResult
    }
    constructor()

    interface eventListener{
        fun onClick()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false)
        return DataViewHolder(view)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {

        user=gitHubResult.items[position]
        val imageUri = Uri.parse(user.avatar_url)
        Log.d("TAG", "url->$imageUri")
        val name = user.login
        holder.tv_name.text = user.login
        Log.d("TAG", "name->$name")
        val score=user.score
        holder.tv_score.text= user.score.toString()
        Log.d("TAG", "score->$score")
        val html = user.html_url
        holder.tv_html.text = html

        val id=user.id
        Log.d("TAG", "id->$id")

        val target = holder.imageView
        Glide.with(context)
            .load(imageUri)
            .fitCenter()
            .centerCrop()
            .override(100, 100)
            .into(target)

        val dbHelper = DbHelper(context, "HUB2.db", null, 1)
        val getdata = dbHelper.getData(userName)
        val getId=dbHelper.getId(id)

        if(id==getId){
            Log.i("TAG","getid::"+getId)
            Log.i("TAG","id::"+id)
            holder.saveBtn.setBackgroundResource(R.drawable.baseline_favorite_black_18dp)
            isLike = true
        }else{
            holder.saveBtn.setBackgroundResource(R.drawable.baseline_favorite_border_black_18dp)
//            isLike = false
        }


        holder.saveBtn.setOnClickListener {
            if (isLike == false) {
                user=gitHubResult.items[position]
                saveLike(user.login, user.avatar_url, user.html_url, user.score, user.id)
                holder.saveBtn.setBackgroundResource(R.drawable.baseline_favorite_black_18dp)
                isLike = true
            } else {
                holder.saveBtn.setBackgroundResource(R.drawable.baseline_favorite_border_black_18dp)
                cancleLike(user.login)
                isLike = false
            }
        }



    }


    private fun cancleLike(name: String) {
        val dbHelper = DbHelper(context, "HUB2.db", null, 1)
        dbHelper.delete(name)
        Toast.makeText(context,"좋아요 취소",Toast.LENGTH_SHORT).show()
    }

    private fun saveLike(name: String, url: String, html: String, etc: Float, id:Int) {
        val dbHelper = DbHelper(context, "HUB2.db", null, 1)
        dbHelper.insert(name, url, html, etc, id)
        Toast.makeText(context,"좋아요",Toast.LENGTH_SHORT).show()
    }

    override fun getItemCount(): Int {
        return gitHubResult.items.size
    }

//    fun checkLike(userName: String):Boolean {
//        Log.i("TAG","체크라이크")
//        val dbHelper = DbHelper(context, "HUB.db", null, 1)
//        val getdata = dbHelper.getData(userName)
//        var size=getdata.length
//        Log.i("TAG","getdata정보:"+size)
//
//        var isLike:Boolean
////            if (getdata == userName) {
////            if(getdata==userName&&getdata.length==userName.length){
//        if(getdata.equals(userName)&&getdata.length==size){
//            saveBtn.setBackgroundResource(R.drawable.baseline_favorite_black_18dp)
//            isLike = true
//        }else{
//            saveBtn.setBackgroundResource(R.drawable.baseline_favorite_border_black_18dp)
//            isLike = false
//        }
//        return isLike
//    }


    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal  var tv_name: TextView
        internal  var tv_score: TextView
        internal  var tv_html: TextView
        internal  var imageView: ImageView
        internal  var saveBtn: ImageButton

        init {
            this.tv_name = itemView.findViewById(R.id.repo_name)
            this.imageView = itemView.findViewById(R.id.repo_image)
            this.tv_score = itemView.findViewById(R.id.repo_score)
            this.saveBtn = itemView.findViewById(R.id.saveBtn)
            this.tv_html = itemView.findViewById(R.id.repo_html)

//            checkLike(userName)

        }

//        fun checkLike(userName: String):Boolean {
//            Log.i("TAG","체크라이크")
//            val dbHelper = DbHelper(context, "HUB.db", null, 1)
//            val getdata = dbHelper.getData(userName)
//            var size=getdata.length
//            Log.i("TAG","getdata정보:"+size)
//
//            var isLike:Boolean
////            if (getdata == userName) {
////            if(getdata==userName&&getdata.length==userName.length){
//            if(getdata.equals(userName)&&getdata.length==size){
//                saveBtn.setBackgroundResource(R.drawable.baseline_favorite_black_18dp)
//                isLike = true
//            }else{
//                saveBtn.setBackgroundResource(R.drawable.baseline_favorite_border_black_18dp)
//                isLike = false
//            }
//            return isLike
//        }
    }
}
