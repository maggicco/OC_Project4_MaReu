package com.oc.mareu.ui.mareu;

import android.content.Context;
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

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class ListMeetingRecyclerViewAdapter extends RecyclerView.Adapter<ListMeetingRecyclerViewAdapter.ViewHolder> {

    Context mContext;
    private final List<Meeting> mMeeting;

    public ListMeetingRecyclerViewAdapter(Context mContext, List<Meeting> mMeeting) {
        this.mContext = mContext;
        this.mMeeting = mMeeting;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.fragment_meeting, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ListMeetingRecyclerViewAdapter.ViewHolder holder, int position) {

        Meeting meeting = mMeeting.get(position);

        holder.mColor.setImageResource(R.drawable.ic_circle_green_1_24);
        holder.mRoom.setText(mMeeting.get(position).getRoomName());
        holder.mHour.setText(mMeeting.get(position).getHour());
        holder.mCreator.setText(mMeeting.get(position).getCreator());

        holder.mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteMeetingEvent(meeting));
            }
        });

    }

    @Override
    public int getItemCount() {
        return mMeeting.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mColor;
        private TextView mRoom;
        private TextView mHour;
        private TextView mCreator;
        private ImageButton mDelete;
        //private TextView mMember;


        public ViewHolder(View itemView) {
            super(itemView);

            mColor = (ImageView) itemView.findViewById(R.id.item_room_color);
            mRoom = (TextView) itemView.findViewById(R.id.item_room_name);
            mHour = itemView.findViewById(R.id.item_room_hour);
            mCreator = itemView.findViewById(R.id.item_room_creator);
            //mMember = itemView.findViewById(R.id.item_room_member);
            mDelete = itemView.findViewById(R.id.item_delete_button);

        }
    }
}
