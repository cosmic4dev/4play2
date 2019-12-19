package cosmic.com.mvvmprj.model

import androidx.room.*

@Dao
interface UserDAO {
    @Query("SELECT * FROM USER")
    fun getAll():List<User>

    @Query("DELETE FROM USER WHERE login='id'")
    fun deleteLike(id:Int)

    @Query("")

    @Insert
    fun insert(user:User)

    @Update
    fun update(user:User)

    @Delete
    fun delete(user:User)

}