package com.example.suwa_arana;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;


public class VTouchHelper extends ItemTouchHelper.SimpleCallback {

    private VMyAdapter adapter;


    public VTouchHelper(VMyAdapter adapter) {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.adapter = adapter;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        final int position = viewHolder.getAdapterPosition();
        if(direction == ItemTouchHelper.LEFT){
            //edit
            adapter.updateData(position);
            adapter.notifyDataSetChanged();


        //delete
        }else{
            adapter.deleteData(position);

        }
    }

}

