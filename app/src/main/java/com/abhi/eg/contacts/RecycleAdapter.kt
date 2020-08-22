package com.abhi.eg.contacts


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView



class RecycleAdapter(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private var dataList = mutableListOf<data>()
    private val mLayoutInflater by lazy { LayoutInflater.from(context) }
    companion object{
        private var SHOW=1
        private var LOAD=2
    }

    override fun getItemViewType(position: Int): Int {
        return if (position==dataList.size)
        {
            LOAD
        }else
        {
            SHOW
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == LOAD) {
            LoadMoreViewHolder(mLayoutInflater.inflate(R.layout.progress, parent, false))
        } else {
            ShowDataViewHolder(mLayoutInflater.inflate(R.layout.contact_data, parent, false))

        }
    }
    override fun getItemCount(): Int {
        return dataList.size+1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(LOAD==getItemViewType(position)){
            (holder as LoadMoreViewHolder).bindLoader()
        }else{
            (holder as ShowDataViewHolder).bindData(dataList[position])
        }
    }


}
 class LoadMoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
     fun bindLoader() {
         TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
     }
 }
 class ShowDataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
     fun bindData(data: data) {

     }
 }

