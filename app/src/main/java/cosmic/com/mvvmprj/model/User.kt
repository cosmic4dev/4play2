package cosmic.com.mvvmprj.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class User (
    val login: String,
    val avatar_url: String,
    val html_url: String,
    val score: Float,
    val uid:Int)
{
    @PrimaryKey(autoGenerate = true) var id:Int = 0
}







