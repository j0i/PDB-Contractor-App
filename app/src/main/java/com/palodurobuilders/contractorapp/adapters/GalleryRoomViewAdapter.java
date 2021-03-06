package com.palodurobuilders.contractorapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.palodurobuilders.contractorapp.R;
import com.palodurobuilders.contractorapp.interfaces.IHandleChildRecyclerClick;
import com.palodurobuilders.contractorapp.models.Image;
import com.palodurobuilders.contractorapp.models.Room;

import java.util.List;

public class GalleryRoomViewAdapter extends RecyclerView.Adapter<GalleryRoomViewAdapter.GalleryRoomViewHolder> implements IHandleChildRecyclerClick
{
    // used to share the views between the child and parent recyclerViews
    private final RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    private final List<Room> _RoomList;
    public IHandleChildRecyclerClick _ihandleChildRecylcerClick;

    // because we're loading into a fragment, need context
    public GalleryRoomViewAdapter(List<Room> roomList)
    {
        this._RoomList = roomList;
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
        Room room = _RoomList.get(position);

        // for the created instance get the title and set it as the text for the text view
        viewHolder.GalleryRoomTitle.setText(room.getName());

        // create a layout manager to assign a layout to the recyclerview

        //the assigned layout will be as linearlayout with vert.
        LinearLayoutManager layoutManager = new LinearLayoutManager(viewHolder.GalleryImageRecyclerView.getContext(), LinearLayoutManager.HORIZONTAL,false);

        //Since this is nested, need to define how many child items should be pre-fetched
        //when the child recyclerView is nested inside the parent recyclerView
        //layoutManager.setInitialPrefetchItemCount(room.getImageList().size());

        //create instance of child item view adapter
        //and set its adapter, layout manager and recyclerviewPool
        GalleryImageViewAdaptor galleryImageViewAdaptor = new GalleryImageViewAdaptor(room.getImages());
        galleryImageViewAdaptor.setClickListener(this);

        viewHolder.GalleryImageRecyclerView.setLayoutManager(layoutManager);
        viewHolder.GalleryImageRecyclerView.setAdapter(galleryImageViewAdaptor);
        viewHolder.GalleryImageRecyclerView.setRecycledViewPool(viewPool);

    }
    @Override
    public int getItemCount()
    {
        return _RoomList.size();
    }
    public void setClickListener(IHandleChildRecyclerClick ihandleChildRecylcerClick)
    {
        _ihandleChildRecylcerClick = ihandleChildRecylcerClick;
    }

    @Override
    public void onClick(View view, Image image)
    {
        _ihandleChildRecylcerClick.onClick(view, image);
    }

    public static class GalleryRoomViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView GalleryRoomTitle;
        private final RecyclerView GalleryImageRecyclerView; //child view

        public GalleryRoomViewHolder(@NonNull View itemView)
        {
            super(itemView);
            GalleryRoomTitle = itemView.findViewById(R.id.textView_room_name);
            GalleryImageRecyclerView = itemView.findViewById(R.id.recylerView_room_list);
        }
    }
}
