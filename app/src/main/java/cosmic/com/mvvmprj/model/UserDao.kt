package cosmic.com.mvvmprj.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {
    @Query("SELECT * FROM User")
    fun getAll(): LiveData<List<User>> //관찰 가능한 객체

    @Query("DELETE FROM User")
    fun deleteAll()

    @Insert
    fun insert(todo: User)

    @Update
    fun update(todo: User)

    @Delete
    fun delete(todo: User)
}