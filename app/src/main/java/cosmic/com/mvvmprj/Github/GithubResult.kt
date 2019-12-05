package cosmic.com.mvvmprj.Github

import cosmic.com.mvvmprj.model.User

data class GitHubResult(val total_cout:Int,val incomplete_result:Boolean, val items:List<User>)