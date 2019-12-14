package cosmic.com.mvvmprj.model

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import cosmic.com.mvvmprj.Github.GithubOwner
import java.util.*

class DbHelper(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {

    val dataList: ArrayList<GithubOwner>
        get() {

            val dataList = ArrayList<GithubOwner>()

            val db = readableDatabase
            val cursor = db.rawQuery("SELECT * FROM HUB2", null)
            if (cursor.moveToNext()) {
                do {
                    val userData = GithubOwner()
                    userData.login=cursor.getString(1)
                    userData.avatar_url = cursor.getString(2)
                    userData.html_url = cursor.getString(3)
                    userData.score = cursor.getFloat(4)
                    userData.id=cursor.getInt(5)


                    dataList.add(userData)
                } while (cursor.moveToNext())

            }
            db.close()

            return dataList
        }


    override fun onCreate(db: SQLiteDatabase) {

        db.execSQL("CREATE TABLE HUB2 (_id INTEGER PRIMARY KEY AUTOINCREMENT, login TEXT, avatar_url TEXT, html_url TEXT, score FLOAT, id INTEGER);")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }

    fun insert(name: String, url: String, html: String, etc: Float, id:Int) {

        val db = writableDatabase
        db.execSQL("INSERT INTO HUB2 VALUES(null, '$name', '$url', '$html', '$etc', '$id');")
    }

    fun getData(name: String): String {
        val db = readableDatabase
        var result = ""

        try {
            val cursor = db.rawQuery("SELECT * FROM HUB2 WHERE login='$name';", null)
            while (cursor.moveToNext()) {
                result = cursor.getString(1)
            }
        } catch (e: Exception) {

        }

        db.close()

        return result
    }

    fun getId(id:Int): Int {
        val db = readableDatabase

        var result=0
        try {
            val cursor = db.rawQuery("SELECT * FROM HUB2 WHERE id='$id';", null)
            while (cursor.moveToNext()) {
                result = cursor.getInt(5)//id 값만 골내기
            }
        } catch (e: Exception) {

        }

        db.close()

//        Log.i("TAG","getId???->"+result)
        return result
    }

    fun delete(name: String) {
        val db = writableDatabase
        db.execSQL("DELETE FROM HUB2 WHERE login='$name';")
        db.close()
    }

    companion object {
        internal val TAG = "DBHelper"
    }
}
