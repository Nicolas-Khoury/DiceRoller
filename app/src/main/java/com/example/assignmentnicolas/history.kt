package com.example.assignmentnicolas

import android.graphics.Canvas
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class history : AppCompatActivity() {

     private lateinit var historyadapter : RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        val recyclerview:RecyclerView= findViewById(R.id.recyclerViewHistory)

        recyclerview.layoutManager = LinearLayoutManager(this@history)
        historyadapter = RecyclerAdapter()

        val swipeGesture = object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT)
        {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean
            {
                return false
            }
            override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean)
            {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int)
            {
                when (direction)
                {
                    ItemTouchHelper.LEFT ->
                    {
                        historyadapter.deleteItem(viewHolder.adapterPosition)
                    }
                }
            }
        }
        val touchHelper = ItemTouchHelper(swipeGesture)
        touchHelper.attachToRecyclerView(recyclerview)

        recyclerview.adapter=historyadapter
        historyadapter.sbmt(ResultsList.results)

    }
    fun btnRemove(sender: View){
        ResultsList.results.clear()
        findViewById<RecyclerView>(R.id.recyclerViewHistory).removeAllViews()
    }
}