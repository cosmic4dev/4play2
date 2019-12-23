package cosmic.com.mvvmprj.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.room.Room
import cosmic.com.mvvmprj.model.AppDatabase
import cosmic.com.mvvmprj.model.User

class SearchViewModel(application: Application):AndroidViewModel(application) {

    private val db= Room.databaseBuilder(
        application,
        AppDatabase::class.java,"HUB3.db"
    ).build()

    var users:LiveData<List<User>>

    var newUser:String?=null

    init {
        users=getAll()
    }

    //일단 싱글톤 안쓰고.
    fun getAll(): LiveData<List<User>>{
        return db.userDao().getAll()
    }



}