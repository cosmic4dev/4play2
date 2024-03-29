package cosmic.com.mvvmprj.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import cosmic.com.mvvmprj.Github.GithubOwner
import cosmic.com.mvvmprj.adapter.DataAdapter2
import cosmic.com.mvvmprj.adapter.MyPagerAdapter
import cosmic.com.mvvmprj.model.DbHelper
import cosmic.com.mvvmprj.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.fragment_like.*


class SearchActivity: AppCompatActivity()  {

    internal lateinit var mainPresenter:MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(cosmic.com.mvvmprj.R.layout.activity_search)

        supportFragmentManager.beginTransaction()
            .replace(cosmic.com.mvvmprj.R.id.viewPager, Fragment_search())
            .commit()

        val fragmentAdapter= MyPagerAdapter(supportFragmentManager)
        viewPager.adapter=fragmentAdapter

        tabLayout.setupWithViewPager(viewPager)

        //1212
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {

                var temList:ArrayList<GithubOwner> = ArrayList()
                //room ->databaseprovider로 이동 (싱글톤처리)
//                val db = Room.databaseBuilder(
//                    applicationContext,
//                    AppDatabase::class.java, "database-name"
//                ).allowMainThreadQueries().build()

//                temList=viewModel.getAll()

//                temList= TodoDAO.getAll()
//                Log.i("TAG","temListSize::"+temList.size)
//                temList=UserDAO.getAll()

// 기존 sqlite
                var dbHelper = DbHelper(applicationContext,"HUB2.db", null, 1)
                for(item in dbHelper.dataList) {
                    temList.add(item)
                }

                val adapter = DataAdapter2(applicationContext,temList)
                recyclerView_search2.adapter=adapter
                adapter.notifyDataSetChanged()


            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })

    }

}