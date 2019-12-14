package cosmic.com.mvvmprj.view

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import cosmic.com.mvvmprj.Github.GitHubResult
import cosmic.com.mvvmprj.R
import cosmic.com.mvvmprj.adapter.DataAdapter
import cosmic.com.mvvmprj.contract.MainContract
import cosmic.com.mvvmprj.presenter.MainPresenter
import kotlinx.android.synthetic.main.fragment_search.*


class Fragment_search: Fragment(),MainContract.view {

    internal lateinit var mainPresenter: MainPresenter

    lateinit var searchUserName:String


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_search, container, false)

        mainPresenter=MainPresenter(this)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager=LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        recyclerView_search1.layoutManager=layoutManager

        inputText?.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchUserName=inputText.text.toString()
                mainPresenter.searchData(searchUserName)
                true
            } else {
                false
            }
        }

        searchBtn.setOnClickListener {
            searchUserName=inputText.text.toString()
            mainPresenter.searchData(searchUserName)

        }

        delButton.setOnClickListener{
            inputText.text.clear()
        }

    }

    override fun sendToAdapter(dataList: GitHubResult) {
        val linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerView_search1.layoutManager = linearLayoutManager
        val adapter = DataAdapter(context,dataList,searchUserName)
        recyclerView_search1.adapter = adapter

        //1212
        adapter.notifyDataSetChanged()
//        Toast.makeText(activity,"tab2",Toast.LENGTH_SHORT).show()

    }

    override fun showToast(msg: String) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }

    override fun closeKeyboard() {
        val imm = activity!!.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(inputText.getWindowToken(), 0);
    }
}