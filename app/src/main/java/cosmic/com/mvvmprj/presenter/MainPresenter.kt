package cosmic.com.mvvmprj.presenter

import cosmic.com.mvvmprj.Github.GithubClient
import cosmic.com.mvvmprj.contract.MainContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainPresenter(private val mainView: MainContract.view) : MainContract.presenter {

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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getJsonData(searchUserName: String) {

    }


}
