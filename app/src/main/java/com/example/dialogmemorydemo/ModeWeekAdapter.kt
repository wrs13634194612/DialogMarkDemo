package com.example.dialogmemorydemo


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_date.view.*

/**
 * 文件：BillAdapter
 * 时间：2018/8/17.
 * 备注：
 */
class ModeWeekAdapter(private var brands: List<ModeIcon>) :
    BaseAdapter<ModeWeekAdapter.ViewHolder>() {

    override fun onBindHolder(holder: ViewHolder, position: Int) {
        val icon = brands[position]
        holder.iv.setImageResource(
            if (icon.isCheck) {
                R.mipmap.btn_add_checked
            } else {
                R.mipmap.btn_add_unchecked
            }
        )
        holder.tvDate.text = icon.equipmentModelIcon
    }


    override fun getItemCount(): Int {
        return brands.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_date, parent, false)
        )
    }

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val tvDate = item.tv_date_item_date!!
        val iv = item.iv_image_item_date!!

    }

}