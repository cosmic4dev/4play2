package cosmic.com.mvvmprj.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import cosmic.com.mvvmprj.R
import kotlinx.android.synthetic.main.activity_book.*

class BookActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)

        var officeName=intent.getStringExtra("officename")

        tv_book_title.setText(officeName)


        bookBtn.setOnClickListener {
            bookOffice()
            Toast.makeText(applicationContext,"예약됨",Toast.LENGTH_SHORT).show()
        }
    }

    private fun bookOffice() {

        var officeName="office2"
    }
}
