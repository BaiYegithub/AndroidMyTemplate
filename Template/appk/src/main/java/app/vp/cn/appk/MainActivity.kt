package app.vp.cn.appk

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //类型后面加?表示可为空
        var age: String? = null
        //抛异常
        val ages = age!!.toInt()
        //返回null
        val age1 = age?.toInt()
        //加?: 如果为 null 则返回-1
        val age2 = age?.toInt() ?: -1
        Log.i("bai", "onCreate: age1是" + age1 +
                "age2是" + age2)



    }
}