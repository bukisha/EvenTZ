package com.example.bookee.eventz.eventlist;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.bookee.eventz.R;
import com.example.bookee.eventz.data.Event;

import java.util.ArrayList;


class EventListCardAdapter extends RecyclerView.Adapter<EventListCardAdapter.EventViewHolder> {
    private ArrayList<Event> events;
    private Context context;
    private RecyclerViewOnItemClickListener recyclerViewOnItemClickListener;
    private ProgressBar progressBar;
    private RecyclerView parentRecyclerView;

    EventListCardAdapter(ArrayList<Event> events, Context context, ProgressBar progressBar, RecyclerView parentRecyclerView) {
        this.events = events;
        this.context = context;
        this.progressBar = progressBar;
        this.parentRecyclerView = parentRecyclerView;
        progressBar.setVisibility(View.VISIBLE);
        parentRecyclerView.setVisibility(View.INVISIBLE);
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(context).inflate(R.layout.event_item_card, parent, false);
        return new EventViewHolder(cardView, recyclerViewOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {
        holder.eventName.setText(events.get(position).getName().getText());
        holder.currentEvent = events.get(position);

        RequestListener<String, GlideDrawable> listener = new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                progressBar.setVisibility(View.GONE);
                parentRecyclerView.setVisibility(View.VISIBLE);
                return false;
            }
        };
        if (events.get(position).getLogo() != null) {
            Glide.with(context).load(events.get(position).getLogo().getUrl()).listener(listener).into(holder.eventCardBackground);
        } else {
            Glide.with(context).load(R.drawable.party_temp).into(holder.eventCardBackground);
        }
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public void setOnClickLister(RecyclerViewOnItemClickListener recyclerViewOnItemClickListener) {

        this.recyclerViewOnItemClickListener = recyclerViewOnItemClickListener;
    }

    static class EventViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView eventCardBackground;
        TextView eventName;
        Event currentEvent;

        EventViewHolder(View itemView, final RecyclerViewOnItemClickListener listener) {
            super(itemView);
            cardView = itemView.findViewById(R.id.event_card_item);
            eventCardBackground = itemView.findViewById(R.id.event_card_background);
            eventName = itemView.findViewById(R.id.event_card_name);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(currentEvent.getName().getText());
                }
            });
        }
    }
}
