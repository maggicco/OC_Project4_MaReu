package com.oc.mareu.ui.mareu;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.oc.mareu.R;
import com.oc.mareu.event.DeleteMeetingEvent;
import com.oc.mareu.model.Meeting;
import com.oc.mareu.service.MeetingApiService;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class ListMeetingRecyclerViewAdapter extends RecyclerView.Adapter<ListMeetingRecyclerViewAdapter.ViewHolder>
implements Filterable{


    private List<Meeting> mMeetings;
    private List<Meeting> mMeetingsFullList;
    private ArrayList<Meeting> dataSet;
    private ArrayList<Meeting> FullList;



    public ListMeetingRecyclerViewAdapter(List<Meeting> mMeetings) {

        this.mMeetings = mMeetings;
        mMeetingsFullList = new ArrayList<>(mMeetings);


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_meeting, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ListMeetingRecyclerViewAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Meeting meeting = mMeetings.get(position);


        if (meeting.getColor().equals("vert")) {
            holder.mColor.setImageResource(R.drawable.ic_circle_green_1_24);
        }
        if (meeting.getColor().equals("rouge")) {
            holder.mColor.setImageResource(R.drawable.ic_circle_red_1_24);
        }
        if (meeting.getColor().equals("orange")) {
            holder.mColor.setImageResource(R.drawable.ic_circle_lightorange_1_24);
        }

        holder.mRoom.setText(meeting.getRoomName());
        holder.mHour.setText(meeting.getHour());
        holder.mCreator.setText(meeting.getMeetingCreator());
        holder.mMember.setText(meeting.getMembers());

        holder.mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EventBus.getDefault().post(new DeleteMeetingEvent(meeting));
                notifyItemRemoved(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMeetings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mColor;
        private TextView mRoom;
        private TextView mHour;
        private TextView mCreator;
        private TextView mMember;
        private ImageButton mDelete;

        public ViewHolder(View itemView) {
            super(itemView);

            mColor = itemView.findViewById(R.id.item_room_color);
            mRoom = itemView.findViewById(R.id.item_room_name);
            mHour = itemView.findViewById(R.id.item_room_hour);
            mCreator = itemView.findViewById(R.id.item_room_creator);
            mMember = itemView.findViewById(R.id.item_room_members);
            mDelete = itemView.findViewById(R.id.item_delete_button);

        }
    }

//    // TODO: 12/08/2021 Filters to fix
//    @Override
//    public Filter getFilter() {
//        return Searched_Filter;
//    }
//    private Filter Searched_Filter = new Filter() {
//        @Override
//        protected FilterResults performFiltering(CharSequence constraint) {
//            ArrayList<Meeting> filteredList = new ArrayList<>();
//            if (constraint == null || constraint.length() == 0) {
//                filteredList.addAll(mMeetings);
//            } else {
//                String filterPattern = constraint.toString().toLowerCase().trim();
//                for (Meeting item : mMeetings) {
//                    if (item.getRoomName().toLowerCase().contains(filterPattern)) {
//                        filteredList.add(item);
//                    }
//                }
//            }
//            FilterResults results = new FilterResults();
//            results.values = filteredList;
//            return results;
//        }
//        @Override
//        protected void publishResults(CharSequence constraint, FilterResults results) {
//            mMeetingsFullList.clear();
//            mMeetingsFullList.addAll((ArrayList) results.values);
//            notifyDataSetChanged();
//        }
//    };
    @Override
    public Filter getFilter() {
        return Searched_Filter;
    }
    private Filter Searched_Filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Meeting> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(mMeetingsFullList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Meeting item : mMeetingsFullList) {
                    if (item.getRoomName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mMeetings.clear();
            mMeetings.addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }
    };


}



