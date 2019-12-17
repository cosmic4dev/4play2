package cosmic.com.mvvmprj.view

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cosmic.com.mvvmprj.R
import cosmic.com.mvvmprj.contract.MainContract
import cosmic.com.mvvmprj.contract.SecondContract
import cosmic.com.mvvmprj.model.Office
import cosmic.com.mvvmprj.model.OfficeList
import cosmic.com.mvvmprj.presenter.SecondPresenter
import cosmic.com.pkwprj.adapter.ProgressAdapter
import kotlinx.android.synthetic.main.activity_ui.*
import java.text.SimpleDateFormat
import java.util.*

class SecondActivity : AppCompatActivity(), SecondContract.View,MainContract,
    ProgressAdapter.ItemClickListener {

    internal var officeList: OfficeList? = null
    internal lateinit var list: ArrayList<Office>
    internal lateinit var adjustTime: String
    internal val adapter by lazy { ProgressAdapter(this,list).apply { setOnClickListener(this@SecondActivity) } }
    internal lateinit var secondPresenter: SecondPresenter
    private val mResources: Resources? = null

     val currentTime: String
        get() {
            val dateFormat = SimpleDateFormat("HHmm")
            val cTime = System.currentTimeMillis()

            return dateFormat.format(cTime)

        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ui)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        secondPresenter = SecondPresenter(this)


        val layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
        recyclerView_office.layoutManager = layoutManager

//        val getTime = currentTime
        val getTime="0900"
        var adjustTime=avaibleTimeCheck(getTime)

        list=secondPresenter.newgetJsonString(adjustTime,resources);
        showOfficeTimeTable(adjustTime)

        var adapter=ProgressAdapter(this,list)

        adapter.setOnClickListener(this)

    }

    private fun showOfficeTimeTable(adjustTime: String) {

        for (i in list.indices) {
            val office = list[i]
            val name = office.name
            val reservationList = office.reservations

            for (j in reservationList!!.indices) {
                val reservationStaus = reservationList[j]

                val startTime = reservationStaus.startTime

                val compare1 = Integer.valueOf(startTime!!)
                val compare2 = Integer.valueOf(adjustTime)

                if (compare1 >= compare2 || compare1 == 1700 && compare2 < 1800) {

                    if (compare1 == compare2) {
                        nonShowTopOfficeList(name!!)
                    } else if (compare1 == 1700 && compare2 == 1730) {
                        nonShowTopOfficeList(name!!)
                    }
                    val endTime = reservationStaus.endTime

                    val a = secondPresenter.processConvert1(startTime)
                    val b = secondPresenter.processConvert2(endTime!!)

                    secondPresenter.MakeMapData(a, b, name)
                } else if (compare2 >= 1800) {
                    tv_office1.visibility = View.GONE
                    tv_office2.visibility = View.GONE
                    tv_office3.visibility = View.GONE
                    tv_office4.visibility = View.GONE
                    tv_office5.visibility = View.GONE
                }
            }
        }

    }

    private fun nonShowTopOfficeList(officename: String) {

        when (officename) {
            "회의실1" -> tv_office1.visibility = View.GONE
            "회의실2" -> tv_office2.visibility = View.GONE
            "회의실3" -> tv_office3.visibility = View.GONE
            "회의실4" -> tv_office4.visibility = View.GONE
            "회의실5" -> tv_office5.visibility = View.GONE
        }

    }



     fun avaibleTimeCheck(time: String):String {

        val hourTime = time.substring(0, 2)
        val minuteTime = time.substring(2)
        val mTime = Integer.valueOf(minuteTime)

        if (mTime < 30) {
            val preMtime = "00"
            adjustTime = hourTime + preMtime

        } else if (30 <= mTime && mTime <= 59) {
            val preMtime = "30"
            adjustTime = hourTime + preMtime

        }

        val TimeConvertToInt = Integer.valueOf(time)
        if (TimeConvertToInt < 900) {
            Log.d(TAG, "아직개업전")
        } else if (900 <= TimeConvertToInt && TimeConvertToInt < 1800) {
            Log.d(TAG, "영업중")
        } else {
            Log.d(TAG, "영업마감")
        }

        return adjustTime
    }

    override fun showAvailableOfficeCode() {

        convertedKey = secondPresenter.processConvert1(adjustTime)

    }

    override fun showList() {

        var adapter = ProgressAdapter(this, list)
        recyclerView_office.setAdapter(adapter)
    }



    companion object {

        internal val TAG = "Main"
        var convertedKey: Int = 0
    }

    override fun onClick( position: Int) {
    }

}
