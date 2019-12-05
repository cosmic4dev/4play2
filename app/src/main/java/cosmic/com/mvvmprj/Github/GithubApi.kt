package cosmic.com.mvvmprj.Github

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {

    @GET("search/users")
    fun getUserInfo(@Query("q")query:String):Single<GitHubResult>
}