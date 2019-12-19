package cosmic.com.mvvmprj.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel

class BookViewModel(application: Application):AndroidViewModel(application) {

    fun addBookList(){

        Log.i("TAG","통과됨")
    }
}