package cosmic.com.mvvmprj.presenter

import android.content.Context
import cosmic.com.mvvmprj.Github.GithubClient
import cosmic.com.mvvmprj.contract.MainContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainPresenter(private val mainView: MainContract.view) : MainContract.presenter {

    lateinit var context:Context
    override fun searchData(searchUserName: String) {

         GithubClient().getApi().getUserInfo(searchUserName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                    data->mainView.sendToAdapter(data)
            },{ error->
                error.printStackTrace()
            })
    }

    override fun getLikeData() {

    }

    override fun getJsonData(searchUserName: String) {

    }


}
