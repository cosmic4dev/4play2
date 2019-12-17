package cosmic.com.mvvmprj.model

import android.view.View
import android.widget.Toast

data class Book(val name:String, val time:String) {

    fun onClickListener(view: View){

        Toast.makeText(view.context,"클릭: $name $time ",Toast.LENGTH_SHORT).show()
    }
}