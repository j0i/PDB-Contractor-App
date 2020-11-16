package com.palodurobuilders.contractorapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.palodurobuilders.contractorapp.R;
import com.palodurobuilders.contractorapp.models.GalleryImage;
import com.palodurobuilders.contractorapp.models.GalleryRoom;

import java.util.List;

public class GalleryRoomViewAdapter extends RecyclerView.Adapter<GalleryRoomViewAdapter.GalleryRoomViewHolder>
{
    // used to share the views between the child and parent recyclerViews
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    private List<GalleryRoom> _RoomList;

    // because we're loading into a fragment, need context
    public GalleryRoomViewAdapter(Context context, List<GalleryRoom> galleryRoomList)
    {
        this._RoomList = galleryRoomList;
    }

    @NonNull
    @Override
    public GalleryRoomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        //inflate the corresponding layout of the galleryRoom item
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_gallery_room, viewGroup, false);
        return new GalleryRoomViewHolder(view);
    }
    @Override
    public void onBindViewHolder(GalleryRoomViewHolder viewHolder, int position)
    {
        //create instance of the GalleryRoom (parent item) class for the given position
        GalleryRoom galleryRoom = _RoomList.get(position);

        // for the created instance get the title and set it as the text for the text view
        viewHolder.GalleryRoomTitle.setText(galleryRoom.getRoomTitle());

        // create a layout manager to assign a layout to the recyclerview

        //the assigned layout will be as linearlayout with vert.
        LinearLayoutManager layoutManager = new LinearLayoutManager(viewHolder.GalleryImageRecyclerView.getContext(), LinearLayoutManager.HORIZONTAL,false);

        //Since this is nested, need to define how many child items should be pre-fetched
        //when the child recyclerView is nested inside the parent recyclerView
        layoutManager.setInitialPrefetchItemCount(galleryRoom.getGalleryImageList().size());

        //create instance of child item view adapter
        //and set its adapter, layout manager and recyclerviewPool
        GalleryImageViewAdaptor galleryImageViewAdaptor = new GalleryImageViewAdaptor(galleryRoom.getGalleryImageList());

        viewHolder.GalleryImageRecyclerView.setLayoutManager(layoutManager);
        viewHolder.GalleryImageRecyclerView.setAdapter(galleryImageViewAdaptor);
        viewHolder.GalleryImageRecyclerView.setRecycledViewPool(viewPool);

    }
    @Override
    public int getItemCount()
    {
        return _RoomList.size();
    }

    public static class GalleryRoomViewHolder extends RecyclerView.ViewHolder
    {
        private TextView GalleryRoomTitle;
        private RecyclerView GalleryImageRecyclerView; //child view

        public GalleryRoomViewHolder(@NonNull View itemView)
        {
            super(itemView);
            GalleryRoomTitle = itemView.findViewById(R.id.textView_room_name);
            GalleryImageRecyclerView = itemView.findViewById(R.id.recylerView_room_list);
        }
    }
}