package cosmic.com.mvvmprj.adapter

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import cosmic.com.mvvmprj.view.Fragment_like
import cosmic.com.mvvmprj.view.Fragment_search


class MyPagerAdapter:FragmentPagerAdapter {

    var data1 : Fragment = Fragment_search()
    var data2 : Fragment = Fragment_like()

    private val tabTitles = arrayOf("Search Tab", "Like Tab")

    var mData : ArrayList<Fragment> = arrayListOf(data1,data2)

    constructor(fm : FragmentManager) : super(fm){

    }


    override fun getItem(position: Int): Fragment {
        Log.i("TAG","탭 클릭됨")
        return mData.get(position)
    }

    override fun getCount(): Int {
        return mData.size
    }

    override fun getPageTitle(position: Int): CharSequence? {

        Log.i("TAG","탭 클릭됨")
        return tabTitles[position]
    }
}