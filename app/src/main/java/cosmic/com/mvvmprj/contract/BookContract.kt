package cosmic.com.mvvmprj.contract

import android.content.res.Resources
import cosmic.com.mvvmprj.model.Office

interface BookContract {

    interface view{
        fun setImage()
        fun showAlert()
    }

    interface presenter{
        fun comSeletedTime()
        fun processConvert2(et:String): Int
        fun processConvert1(st:String): Int
        fun newgetJsonString(time:String,resouce: Resources): ArrayList<Office>
        fun MakeMapData(startPoint: Int, endPoint: Int, officeName: String?)
    }
}