package com.example.assignmentnicolas

import android.graphics.Canvas
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import java.nio.file.Files.size


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
        get(historyadapter)
        historyadapter.sbmt(ResultsList.results)

    }
    fun btnRemove(sender: View){
        ResultsList.results.clear()
        findViewById<RecyclerView>(R.id.recyclerViewHistory).removeAllViews()
    }

    private fun get(historyadapter: RecyclerAdapter)
    {
        val queue : RequestQueue = Volley.newRequestQueue(this)
        val url = "https://615f565cf7254d00170680f1.mockapi.io/Results"
        val postRequest = JsonArrayRequest(
        Request.Method.GET, url,
        null,
        {
                Response -> Log.d("1", Response.toString())

            for (i in 0 until Response.length())
            {
                val jsonObject = Response.getJSONObject(i)
                val diceArray: JSONArray= jsonObject.getJSONArray("Results")
                val dicetype : String =jsonObject.getString("typeroll")
                val countroll :Int =jsonObject.getInt("rollcount")
                val diceMutableList: MutableList<Int> = mutableListOf()
                for (j in 0 until diceArray.length())
                {
                    diceMutableList.add(diceArray.get(j) as Int)
                }
                ResultsList.results.add(diceHistoryType(diceMutableList,dicetype, countroll))
            }
            historyadapter.notifyDataSetChanged()
        },
        {
                error -> Log.d("0", error.toString())
        })

        queue.add(postRequest)
    }


}