package com.buap.arpavisos;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.buap.arpavisos.databinding.ItemEventCarouselBinding;
import java.util.List;

public class EventCarouselAdapter extends RecyclerView.Adapter<EventCarouselAdapter.ViewHolder> {

    private final List<NewsItem> eventList;

    public EventCarouselAdapter(List<NewsItem> eventList) {
        this.eventList = eventList;
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
