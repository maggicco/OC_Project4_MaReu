package com.oc.mareu.ui.mareu;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.util.List;

public class ListMeetingRecyclerViewAdapter extends RecyclerView.Adapter<ListMeetingRecyclerViewAdapter.ViewHolder> {

    private final List<Meeting> mMeetings;
    private MeetingApiService mApiService;


    public ListMeetingRecyclerViewAdapter(List<Meeting> mMeetings) {

        this.mMeetings = mMeetings;

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


        if (meeting.getColor() == "vert") {
            holder.mColor.setImageResource(R.drawable.ic_circle_green_1_24);
        }
        if (meeting.getColor() == "rouge") {
            holder.mColor.setImageResource(R.drawable.ic_circle_red_1_24);
        }
        if (meeting.getColor() == "orange") {
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
}
