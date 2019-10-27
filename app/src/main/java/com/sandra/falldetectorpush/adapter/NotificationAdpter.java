package com.sandra.falldetectorpush.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import com.sandra.falldetectorpush.R;
import com.sandra.falldetectorpush.model.Notification;

import java.util.ArrayList;

public class NotificationAdpter extends RecyclerView.Adapter<NotificationAdpter.NotificationViewHolder> {

    private ArrayList<Notification> notifications;

    public NotificationAdpter(ArrayList<Notification> notifications){
        this.notifications = notifications;
    }

    @NonNull
    @Override
    public NotificationAdpter.NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemNotification = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_notification_item,parent,false);
        return new NotificationViewHolder(itemNotification);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdpter.NotificationViewHolder contactViewHolder, int i) {
        Notification n = notifications.get(i);
        if (n!= null){
            contactViewHolder.description.setText(n.getDescription());
            contactViewHolder.date.setText(n.getDate());
        }
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    public class NotificationViewHolder extends RecyclerView.ViewHolder{

        TextView description;
        TextView date;


        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.tv_description);
            date = itemView.findViewById(R.id.tv_date);


        }
    }
}