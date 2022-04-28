package com.example.dialogmemorydemo


import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {
    private val list = mutableListOf<String>()

    private val dateList = mutableListOf<ModeIcon>()

    private var urlData = "1,3,5,6,7";


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        Log.e("TAG", "" + dateList + "\t")

        //初始化  字符串为null  传默认的 全部为false
        dateList.add(ModeIcon("1icon", "1week", false, 1))
        dateList.add(ModeIcon("2icon", "2week", false, 2))
        dateList.add(ModeIcon("3icon", "3week", false, 3))
        dateList.add(ModeIcon("4icon", "4week", false, 4))
        dateList.add(ModeIcon("5icon", "5week", false, 5))
        dateList.add(ModeIcon("6icon", "6week", false, 6))
        dateList.add(ModeIcon("7icon", "7week", false, 7))
        if (urlData.isNotEmpty()) {
            //做判断   如果字符串比对成功的   为true 没有比对的数据  全部为false
            for (i in dateList.indices) {
                dateList[i].isCheck = urlData.contains(dateList[i].day.toString())
            }
        }

        Log.e("TAG", "$dateList")

        btn_main_intent.setOnClickListener {
            val mSelectWeekDialog = SelectWeekDialog.newInstance()

            mSelectWeekDialog.setSourceData(dateList)

            mSelectWeekDialog.setListen { dateString, dateInt, dates ->
                urlData = dateInt;
                list.addAll(Arrays.asList<String>(*dateInt.split(",").toTypedArray()))
                for (i in dates.indices) {
                    dates[i].isCheck = urlData.contains(dates[i].day.toString())
                }
                Log.e("TAG", "$dates")
            }
            mSelectWeekDialog.show(supportFragmentManager, "")
        }
    }
}