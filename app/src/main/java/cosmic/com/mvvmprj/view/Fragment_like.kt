package cosmic.com.mvvmprj.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import cosmic.com.mvvmprj.Github.GitHubResult
import cosmic.com.mvvmprj.Github.GithubOwner
import cosmic.com.mvvmprj.R
import cosmic.com.mvvmprj.adapter.DataAdapter2
import cosmic.com.mvvmprj.contract.MainContract
import cosmic.com.mvvmprj.model.DbHelper
import cosmic.com.mvvmprj.presenter.MainPresenter
import kotlinx.android.synthetic.main.fragment_like.view.*


class Fragment_like: Fragment(),MainContract.view {

    internal var dataList: List<GithubOwner> ?=null
    var presenter: MainPresenter?=null
    var temList:ArrayList<GithubOwner>?=null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_like, container,false)

        rootView.recyclerView_search2.layoutManager = LinearLayoutManager(activity)

        var temList:ArrayList<GithubOwner> = ArrayList()

        //room
//        val db = Room.databaseBuilder(
//            context!!.applicationContext,
//            AppDatabase::class.java, "database-name"
//        ).build()
//
//        for (item in db.userDao().getAll()){
//            temList.add(item)
//        }

        //기본 sqlite 방식
        var dbHelper = DbHelper(activity,"HUB2.db", null, 1)

        for(item in dbHelper.dataList) {
            temList.add(item)
        }

        val adapter = DataAdapter2(context!!,temList)
        rootView.recyclerView_search2.adapter=adapter
//        getLikeData(rootView)
        adapter.notifyDataSetChanged()


        return rootView
    }


//    fun getLikeData(rootView: View) {
//
//        var temList:ArrayList<GithubOwner> = ArrayList()
//
//        var dbHelper = DbHelper(activity,"HUB2.db", null, 1)
//
//        val linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
//
////        recyclerView_search2.layoutManager = linearLayoutManager
//
//        for(item in dbHelper.dataList) {
//            temList.add(item)
//        }
//
//        val adapter = DataAdapter2(context!!,temList)
////        recyclerView.adapter = adapter
//        rootView.recyclerView_search2.adapter=adapter
//
//        adapter.notifyDataSetChanged()
//
//    }


    override fun sendToAdapter(data: GitHubResult) {

    }

    override fun showToast(msg: String) {
    }

    override fun closeKeyboard() {
    }
}