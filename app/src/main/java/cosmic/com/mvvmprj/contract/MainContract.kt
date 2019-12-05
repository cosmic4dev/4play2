package cosmic.com.mvvmprj.contract

import cosmic.com.mvvmprj.Github.GitHubResult

interface MainContract {

    interface view{
        fun showToast(msg:String)
        fun closeKeyboard()
        fun sendToAdapter(data: GitHubResult)
    }

    interface presenter {
        fun searchData(searchUserName:String)
        fun getJsonData(searchUserName: String)
        fun getLikeData()
    }
}
