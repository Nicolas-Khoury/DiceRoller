package com.example.assignmentnicolas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>()
{
    private var diceList: MutableList<diceHistoryType> = mutableListOf()

    fun deleteItem(position: Int)
    {
        diceList.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
    {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.activity_elementlayout, parent, false))
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int)
    {
        when (holder)
        {
           is ViewHolder ->

            {
                holder.bind(diceList[position])
            }
        }

    }

    override fun getItemCount(): Int
    {
        return diceList.size
    }
    fun sbmt(dicelist: MutableList<diceHistoryType>)
    {
        diceList = dicelist
    }

    class ViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        private val elementlist = itemView.findViewById<TextView>(R.id.elementlist)
        private val typeroll = itemView.findViewById<TextView>(R.id.typeRoll)
        private val countroll = itemView.findViewById<TextView>(R.id.countRoll)

        fun bind(rollturn: diceHistoryType)
        {
            elementlist.text = rollturn.elemtnlist.toString()
            typeroll.text = rollturn.typeRoll.toString()
            countroll.text = rollturn.countRoll.toString()
        }
    }

}
















