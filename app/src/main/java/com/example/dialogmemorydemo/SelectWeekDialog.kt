package com.example.dialogmemorydemo


import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.RelativeLayout
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.view_mode_day.*

class SelectWeekDialog : DialogFragment() {

    private lateinit var listen: ((String, String, List<ModeIcon>) -> Unit)
    private val dates = mutableListOf<ModeIcon>()

    fun setListen(listen: ((String, String, List<ModeIcon>) -> Unit)): SelectWeekDialog {
        this.listen = listen
        return this
    }

    private lateinit var mContext: Context


    companion object {
        fun newInstance(): SelectWeekDialog = SelectWeekDialog()
    }

    fun setSourceData(list: List<ModeIcon>) {
        this.dates.addAll(list)
        Log.e("TAG", "${this.dates}")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.view_mode_day, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListen()
        initRv()
    }

    private fun initRv() {
        rv_date_view_mode_day.layoutManager = LinearLayoutManager(context)
        val adapter = ModeWeekAdapter(dates)
        rv_date_view_mode_day.adapter = adapter
        adapter.setOnItemClickListener { item, position ->
            dates[position].isCheck = !dates[position].isCheck
            adapter.notifyDataSetChanged()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val root = RelativeLayout(activity)
        root.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        )

        val dialog = Dialog(mContext)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(root)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        )
        // dialog.window.decorView.setPadding(0, 0, 0, 0);
        return dialog
    }


    private fun initListen() {
        tv_cancel.setOnClickListener {
            dismissAllowingStateLoss()
        }

        tv_done.setOnClickListener {
            val dateString = StringBuffer()
            val dateInt = StringBuffer()
            var count = 0
            Log.e("TAG", "" + dates + "\t")

            dates.forEach {
                if (it.isCheck) {
                    dateString.append(it.week)
                    dateInt.append(it.day)
                    dateString.append(" ")
                    dateInt.append(",")
                    count++
                }
            }

            if (dateString.length > 2) {
                if (count == 7) {
                    listen.invoke(
                        "每天",
                        dateInt.replace(dateInt.length - 1, dateInt.length, "").toString(), dates
                    )
                } else {
                    listen.invoke(
                        dateString.replace(dateString.length - 1, dateString.length, "").toString(),
                        dateInt.replace(dateInt.length - 1, dateInt.length, "").toString(), dates
                    )
                }

            } else {
                listen.invoke("不重复", "0", dates)
            }

            dismissAllowingStateLoss()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

}