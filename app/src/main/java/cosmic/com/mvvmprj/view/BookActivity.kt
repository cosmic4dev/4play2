package cosmic.com.mvvmprj.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import cosmic.com.mvvmprj.R
import cosmic.com.mvvmprj.databinding.ActivityDetailBinding
import cosmic.com.mvvmprj.model.Office
import cosmic.com.mvvmprj.presenter.BookPresenter
import cosmic.com.mvvmprj.viewmodel.BookViewModel
import kotlinx.android.synthetic.main.activity_detail.*

class BookActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener,
    View.OnClickListener {


    private lateinit var secondActivity: SecondActivity
    internal lateinit var bookPresenter: BookPresenter
    internal lateinit var list: ArrayList<Office>
    lateinit var bookList: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_detail)
        var binding =
            DataBindingUtil.setContentView<ActivityDetailBinding>(this, R.layout.activity_detail)

        binding.lifecycleOwner = this //라이브데이터 활용준비

        val viewModel = ViewModelProviders.of(this)[BookViewModel::class.java]
        binding.viewModel = viewModel

        var name = intent.getStringExtra("name")
        tv_book_officeName.setText(name)
        var position = intent.getIntExtra("position", 0)

        bookPresenter = BookPresenter()
        secondActivity = SecondActivity()

        bookList = ArrayList()

        var time = "0900"
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
        t8.setOnClickListener(this)
        t9.setOnClickListener(this)
        t10.setOnClickListener(this)
        t11.setOnClickListener(this)
        t12.setOnClickListener(this)
        t13.setOnClickListener(this)
        t14.setOnClickListener(this)
        t15.setOnClickListener(this)
        t16.setOnClickListener(this)
        t17.setOnClickListener(this)
        t18.setOnClickListener(this)

        book_btn.setOnClickListener {
            Log.i("TAG", "bookListSize: " + bookList.size)

            var result = ""

            for (item in bookList) {
                result += "\n" + "* " + item
            }
//            tv_book_result.text = result

            val alertDialog = AlertDialog.Builder(this)

            alertDialog.setTitle("예약확인")
            alertDialog.setMessage(result)
            alertDialog.setPositiveButton("예약") { _, _ ->
                Toast.makeText(applicationContext, "예약되었습니다.", Toast.LENGTH_SHORT).show()
                //예약 확인서
            }
            alertDialog.setNegativeButton("취소") { _, _ ->
                Toast.makeText(applicationContext, "취소되었습니다.", Toast.LENGTH_SHORT).show()
                //Ui 갱신.코드
                viewModel.cancle()
            }
            alertDialog.show()
        }
    }

    private fun setImage(position: Int) {
        var imageId=0

        when(position){
            0->imageId=R.drawable.conroom1
            1->imageId=R.drawable.conroom2
            2->imageId=R.drawable.conroom3
            3->imageId=R.drawable.conroom4
            4->imageId=R.drawable.conroom5

        }

        val target = iv_conRoom
        Glide.with(applicationContext)
            .load(imageId)
            .fitCenter()
            .centerCrop()
            .override(700, 500)
            .into(target)
    }


    private fun doBook(time: String) {

        if (!bookList.contains(time)) {
            bookList.add(time)
            Log.i("TAG", "추가")
        } else {
            bookList.remove(time)
            Log.i("TAG", "삭제 ")
        }
        return
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.t1 -> {
                t1.setSelected(!t1.isSelected());doBook("0900")
            }
            R.id.t2 -> {
                t2.setSelected(!t2.isSelected());doBook("0930")
            }
            R.id.t3 -> {
                t3.setSelected(!t3.isSelected());doBook("1000")
            }
            R.id.t4 -> {
                t4.setSelected(!t4.isSelected());doBook("1030")
            }
            R.id.t5 -> {
                t5.setSelected(!t5.isSelected());doBook("1100")
            }
            R.id.t6 -> {
                t6.setSelected(!t6.isSelected());doBook("1130")
            }
            R.id.t7 -> {
                t7.setSelected(!t7.isSelected());doBook("1200")
            }
            R.id.t8 -> {
                t8.setSelected(!t8.isSelected());doBook("1230")
            }
            R.id.t9 -> {
                t9.setSelected(!t9.isSelected());doBook("1300")
            }
            R.id.t10 -> {
                t10.setSelected(!t10.isSelected());doBook("1330")
            }
            R.id.t11 -> {
                t11.setSelected(!t11.isSelected());doBook("1400")
            }
            R.id.t12 -> {
                t12.setSelected(!t12.isSelected());doBook("1430")
            }
            R.id.t13 -> {
                t13.setSelected(!t13.isSelected());doBook("1500")
            }
            R.id.t14 -> {
                t14.setSelected(!t14.isSelected());doBook("1530")
            }
            R.id.t15 -> {
                t15.setSelected(!t15.isSelected());doBook("1600")
            }
            R.id.t16 -> {
                t16.setSelected(!t16.isSelected());doBook("1630")
            }
            R.id.t17 -> {
                t17.setSelected(!t17.isSelected());doBook("1700")
            }
            R.id.t18 -> {
                t18.setSelected(!t18.isSelected());doBook("1730")
            }

        }
    }


    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        Toast.makeText(applicationContext, "선택: " + position, Toast.LENGTH_SHORT).show()
    }

    private fun showOfficeTimeTable(position: Int, adjustTime: String) {

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

        for (n in a until b)

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
                10  -> t11.visibility = View.GONE
                11 -> t12.visibility = View.GONE
                12 -> t13.visibility = View.GONE
                13 -> t14.visibility = View.GONE
                14 -> t15.visibility = View.GONE
                15 -> t16.visibility = View.GONE
                16 -> t17.visibility = View.GONE
                17 -> t18.visibility = View.GONE

            }
    }
}
