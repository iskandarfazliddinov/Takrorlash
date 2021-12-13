package com.example.takrorlash

import android.content.Context
import android.graphics.Canvas
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator

abstract class SwipeGesture(context: Context):ItemTouchHelper.SimpleCallback
    (ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.START or ItemTouchHelper.END ,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

    val deletColor = ContextCompat.getColor(context,R.color.deletcolor)
    val archiveColor = ContextCompat.getColor(context,R.color.archinecolor)

    val delet_icon = R.drawable.ic_baseline_delete_24
    val archive_icon = R.drawable.ic_baseline_archive_24



    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        RecyclerViewSwipeDecorator.Builder(c,recyclerView,viewHolder,dX,dY,actionState,isCurrentlyActive)
            .addSwipeLeftActionIcon(delet_icon)
            .addSwipeLeftBackgroundColor(deletColor)
            .addSwipeRightActionIcon(archive_icon)
            .addSwipeRightBackgroundColor(archiveColor)
            .create()
            .decorate()

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }




}