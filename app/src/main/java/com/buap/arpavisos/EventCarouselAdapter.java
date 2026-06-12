package com.buap.arpavisos;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.buap.arpavisos.databinding.ItemEventCarouselBinding;
import java.util.List;

public class EventCarouselAdapter extends RecyclerView.Adapter<EventCarouselAdapter.ViewHolder> {

    public interface OnEventClickListener {
        void onEventClick(NewsItem event);
    }

    private final List<NewsItem> eventList;
    private final OnEventClickListener clickListener;

    public EventCarouselAdapter(List<NewsItem> eventList, OnEventClickListener clickListener) {
        this.eventList = eventList;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemEventCarouselBinding binding = ItemEventCarouselBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NewsItem item = eventList.get(position);
        holder.binding.tvEventTitle.setText(item.getTitle());
        holder.binding.tvEventDate.setText(item.getDate());
        holder.binding.tvEventCategory.setText(item.getCategory().toUpperCase());
        holder.binding.tvEventLocation.setText(item.getDesc());

        if (item.getImageResId() != 0) {
            holder.binding.ivEventImage.setImageResource(item.getImageResId());
            holder.binding.ivEventImage.setVisibility(android.view.View.VISIBLE);
        } else {
            holder.binding.ivEventImage.setVisibility(android.view.View.GONE);
        }

        // Set click listener on the root item view
        holder.itemView.setOnClickListener(v -> {
            if (clickListener != null) {
                clickListener.onEventClick(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ItemEventCarouselBinding binding;

        public ViewHolder(ItemEventCarouselBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
