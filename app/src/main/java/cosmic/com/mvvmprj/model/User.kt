package cosmic.com.mvvmprj.model

import androidx.room.Entity

@Entity
data class User (
    val login: String,
    val avatar_url: String,
    val html_url: String,
    val score: Float,
    val id:Int)







