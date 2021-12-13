package com.example.takrorlash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.takrorlash.Adapter.Adapter
import com.example.takrorlash.Data.Data
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    val data = ArrayList<Data>()
    private lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listImage = listOf<Int>(
            R.drawable.ovqat_1,
            R.drawable.ovqat_10,
            R.drawable.ovqat_3,
            R.drawable.ovqat_4,
            R.drawable.ovqat_5,
            R.drawable.ovqat_6,
            R.drawable.ovqat_7,
            R.drawable.ovqat_8,
            R.drawable.ovqat_9


        )
        val listText_1 = listOf<String>(
            "Cheese",
            "Egg",
            "Butter",
            "Margarine",
            "Yogurt",
            "Cottage cheese",
            "Ice cream",
            "Cream",
            "Sandwich"
        )
        (0..listImage.size-1).forEach { i->
            data.add(Data(listImage[i]!!,listText_1[i]!!))
        }
        val reyclerView: RecyclerView = findViewById(R.id.reyclerview)
        reyclerView.layoutManager = LinearLayoutManager(this)

        adapter = Adapter(data)
        reyclerView.adapter = adapter

        val swipeGesture = object :SwipeGesture(this){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val from_pas = viewHolder.adapterPosition
                val to_pas = target.adapterPosition

                Collections.swap(data,from_pas,to_pas)
                adapter.notifyItemMoved(from_pas,to_pas)
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                when(direction){
                    ItemTouchHelper.LEFT->{
                        adapter.delet(viewHolder.position)
                    }
                    ItemTouchHelper.RIGHT->{
                        val archiveItem = data[viewHolder.adapterPosition]
                        adapter.delet(viewHolder.position)
                        adapter.archive(data.size,archiveItem)
                    }
                }
            }

        }
        val touchHelper = ItemTouchHelper(swipeGesture)
        touchHelper.attachToRecyclerView(reyclerView)

        adapter.SetOnClilcListener{postion,image_id,Text_1->
            val intent = Intent(this,MainActivity2::class.java)
            intent.putExtra("image_id",image_id)
            intent.putExtra("text_1",Text_1)
            startActivity(intent)
        }
    }
}