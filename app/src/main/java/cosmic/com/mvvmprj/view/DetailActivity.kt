package cosmic.com.mvvmprj.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cosmic.com.mvvmprj.R
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        var name=intent.getStringExtra("name")
        tv_c.setText(name)

    }
}
