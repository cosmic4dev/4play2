package cosmic.com.mvvmprj.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cosmic.com.mvvmprj.Github.GitHubResult
import cosmic.com.mvvmprj.Github.GithubOwner
import cosmic.com.mvvmprj.R
import cosmic.com.mvvmprj.adapter.DataAdapter2
import cosmic.com.mvvmprj.contract.MainContract
import cosmic.com.mvvmprj.model.DbHelper
import cosmic.com.mvvmprj.presenter.MainPresenter


class Fragment_like: Fragment(),MainContract.view {

    internal lateinit var recyclerView: RecyclerView
    internal var dataList: List<GithubOwner> ?=null
    var presenter: MainPresenter?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_like, container,false)

        recyclerView=rootView.findViewById(R.id.recyclerView_search2)

        getLikeData()

        return rootView
    }


    private fun getLikeData() {

        var temList:ArrayList<GithubOwner> = ArrayList()

        var dbHelper = DbHelper(activity,"HUB.db", null, 1)

        val linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager

        for(item in dbHelper.dataList) {
            temList.add(item)
        }

        val adapter = DataAdapter2(context!!,temList)
        recyclerView.adapter = adapter

    }

    override fun sendToAdapter(data: GitHubResult) {
    }

    override fun showToast(msg: String) {
    }

    override fun closeKeyboard() {
    }
}