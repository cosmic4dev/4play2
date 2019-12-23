package cosmic.com.pkwprj.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cosmic.com.mvvmprj.model.Office
import cosmic.com.mvvmprj.view.BookActivity
import cosmic.com.mvvmprj.view.SecondActivity
import java.util.*


class ProgressAdapter(internal var context: Context, internal var lists: ArrayList<Office>) :
    RecyclerView.Adapter<ProgressAdapter.ViewHolder>() {

    internal lateinit var office: Office

    private var clickListener: ItemClickListener? = null

    fun setOnClickListener(listener: ItemClickListener) {
        clickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(cosmic.com.mvvmprj.R.layout.item_office, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        office = lists[position]

        holder.tv_officeName.text = office.name
        holder.tv_location.text = office.location
        holder.progressBar3.progressDrawable = office.drawable



        if (0 < SecondActivity.convertedKey && SecondActivity.convertedKey < 18) {
            val inputX = (SecondActivity.convertedKey - 1) * 50
            holder.tv_current.x = inputX.toFloat()
        } else {
            holder.tv_current.text = ""
        }


        holder.itemView.setOnClickListener{

            office=lists[position]
            var intent =Intent(it.context,BookActivity::class.java)
            intent.putExtra("name",office.name)
            intent.putExtra("position",position)
            context.startActivity(intent)

            //test
            clickListener?.onClick(position)
        }

    }


    interface ItemClickListener{
        fun onClick(position: Int)
    }

    override fun getItemCount(): Int {
        return lists.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var progressBar3: ProgressBar
        internal var tv_officeName: TextView
        internal var tv_location: TextView
        internal var tv_current: TextView

        init {

            progressBar3 = itemView.findViewById(cosmic.com.mvvmprj.R.id.progressBar3)
            tv_officeName = itemView.findViewById(cosmic.com.mvvmprj.R.id.tv_officeName)
            tv_location = itemView.findViewById(cosmic.com.mvvmprj.R.id.tv_officeLocation)
            tv_current = itemView.findViewById(cosmic.com.mvvmprj.R.id.tv_move)
        }

    }
}

