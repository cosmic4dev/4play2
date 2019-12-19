package cosmic.com.mvvmprj.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import cosmic.com.mvvmprj.R
import cosmic.com.mvvmprj.databinding.ActivityDetailBinding
import cosmic.com.mvvmprj.model.Office
import cosmic.com.mvvmprj.presenter.BookPresenter
import kotlinx.android.synthetic.main.activity_detail.*
import java.util.*

class DetailActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener,
    View.OnClickListener {


    private lateinit var binding: ActivityDetailBinding
    private lateinit var secondActivity: SecondActivity
    internal lateinit var bookPresenter: BookPresenter
    internal lateinit var list: ArrayList<Office>

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
//        binding= DataBindingUtil.setContentView(this, R.layout.activity_detail)
//        binding.viewmodel= ViewModel()

        var name = intent.getStringExtra("name")
        tv_book_officeName.setText(name)
        var position=intent.getIntExtra("position",0)

        bookPresenter = BookPresenter()
        secondActivity = SecondActivity()

        var time="0900"
//        var time = secondActivity.currentTime
        var adjustTime = secondActivity.avaibleTimeCheck(time)
        list = bookPresenter.newgetJsonString(name, resources);
        showOfficeTimeTable(position, adjustTime)

        setImage(position)

        t1.setOnClickListener(this)
        t2.setOnClickListener(this)
        t3.setOnClickListener(this)
        t4.setOnClickListener(this)
        t5.setOnClickListener(this)
        t6.setOnClickListener(this)
        t7.setOnClickListener(this)

        book_btn.setOnClickListener {
//            var bookTime =
//            doBook(bookTime)

        }


    }

    private fun setImage(position: Int) {

        val target = iv_conRoom
        Glide.with(applicationContext)
            .load(R.drawable.conroom1)
            .fitCenter()
            .centerCrop()
            .override(700, 500)
            .into(target)
    }


    private fun doBook(time: String) {
        //클릭된 버튼 id 정렬 후 맨앞요소값 가져오기

        t1.isSelected = true

        if (t1.isSelected) Toast.makeText(applicationContext, "예약됨: " + time,
            Toast.LENGTH_SHORT
        ).show()


    }

    override fun onClick(v: View) {

        when(v.id){
            R.id.t1 ->  t1.setSelected(!t1.isSelected())
            R.id.t2 ->  t2.setSelected(!t2.isSelected())
            R.id.t3 ->  t3.setSelected(!t3.isSelected())
            R.id.t4 ->  t4.setSelected(!t4.isSelected())
            R.id.t5 ->  t5.setSelected(!t5.isSelected())
            R.id.t6 ->  t6.setSelected(!t6.isSelected())
            R.id.t7 ->  t7.setSelected(!t7.isSelected())
            R.id.t8 ->  t8.setSelected(!t8.isSelected())
            R.id.t9 ->  t9.setSelected(!t9.isSelected())
            R.id.t10 ->  t10.setSelected(!t10.isSelected())
            R.id.t11 ->  t11.setSelected(!t11.isSelected())
            R.id.t12 ->  t12.setSelected(!t12.isSelected())
            R.id.t13 ->  t13.setSelected(!t13.isSelected())
            R.id.t14 ->  t14.setSelected(!t14.isSelected())
            R.id.t15 ->  t15.setSelected(!t15.isSelected())
            R.id.t16 ->  t16.setSelected(!t16.isSelected())
            R.id.t17 ->  t17.setSelected(!t17.isSelected())
            R.id.t18 ->  t18.setSelected(!t18.isSelected())

        }
    }



    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        Toast.makeText(applicationContext, "선택: " + position, Toast.LENGTH_SHORT).show()
    }

    private fun showOfficeTimeTable(position: Int, adjustTime: String) {

//        for (i in list.indices) {
        val office = list[position]
        val name = office.name
        val reservationList = office.reservations

        for (j in reservationList!!.indices) {
            val reservationStaus = reservationList[j]

            val startTime = reservationStaus.startTime

            val compare1 = Integer.valueOf(startTime!!)
            val compare2 = Integer.valueOf(adjustTime)

            if (compare1 >= compare2 || compare1 == 1700 && compare2 < 1800) {
                val endTime = reservationStaus.endTime

                val a = bookPresenter.processConvert1(startTime)
                val b = bookPresenter.processConvert2(endTime!!)

                Log.i("TAG", "시작:" + a)
                Log.i("TAG", "끝:" + b)

                nonShowTopOfficeList(a, b)

            } else if (compare2 >= 1800) {

            }
        }

    }

     fun nonShowTopOfficeList(a: Int, b: Int) {

        for (n in a..b)

            when (n) {
                0 -> t1.visibility = View.GONE
                1 -> t2.visibility = View.GONE
                2 -> t3.visibility = View.GONE
                3 -> t4.visibility = View.GONE
                4 -> t5.visibility = View.GONE
                5 -> t6.visibility = View.GONE
                6 -> t7.visibility = View.GONE
                7 -> t8.visibility = View.GONE
                8 -> t9.visibility = View.GONE
                9 -> t10.visibility = View.GONE
                10 -> t11.visibility = View.GONE
                11 -> t12.visibility = View.GONE
                12 -> t13.visibility = View.GONE
                13 -> t14.visibility = View.GONE
                14 -> t15.visibility = View.GONE
                15 -> t16.visibility = View.GONE
                16 -> t17.visibility = View.GONE
                17 -> t18.visibility = View.GONE
                18 -> t19.visibility = View.GONE

            }
    }
}
