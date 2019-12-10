package cosmic.com.pkwprj.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import cosmic.com.mvvmprj.R
import cosmic.com.mvvmprj.model.Office
import cosmic.com.mvvmprj.view.SecondActivity
import java.util.*


class ProgressAdapter(internal var context: Context, internal var lists: ArrayList<Office>) :
    RecyclerView.Adapter<ProgressAdapter.ViewHolder>() {

    internal lateinit var office: Office

    internal var clickListener: ClickListener? = null




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_office, parent, false)

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
            clickListener?.onItemClicked(office.name)

            Toast.makeText(context, "click: "+position,Toast.LENGTH_SHORT).show()
        }

    }

    interface ClickListener {
        fun onItemClicked(name: String?)
    }

    fun setItemClickListener(clickListener: ClickListener?){
        this.clickListener=clickListener
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

            progressBar3 = itemView.findViewById(R.id.progressBar3)
            tv_officeName = itemView.findViewById(R.id.tv_officeName)
            tv_location = itemView.findViewById(R.id.tv_officeLocation)
            tv_current = itemView.findViewById(R.id.tv_move)
        }

    }
}

