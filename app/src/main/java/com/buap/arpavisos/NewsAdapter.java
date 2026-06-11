package com.buap.arpavisos;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.buap.arpavisos.databinding.ItemNewsBinding;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private final List<NewsItem> newsList;

    public NewsAdapter(List<NewsItem> newsList) {
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemNewsBinding binding = ItemNewsBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NewsItem item = newsList.get(position);
        holder.binding.tvTitle.setText(item.getTitle());
        holder.binding.tvDate.setText(item.getDate());
        holder.binding.tvDesc.setText(item.getDesc());
        holder.binding.tvCategory.setText(item.getCategory().toUpperCase());

        // Dynamic branding color by category for a rich visual design
        int colorAccent = holder.itemView.getContext().getResources().getColor(R.color.colorAccent);
        int colorPrimary = holder.itemView.getContext().getResources().getColor(R.color.colorPrimary);
        int colorTeal = 0xFF00796B; // General info

        if ("CONVOCATORIA".equalsIgnoreCase(item.getCategory())) {
            holder.binding.tvCategory.setTextColor(colorAccent);
            holder.binding.viewAccent.setBackgroundColor(colorAccent);
        } else if ("EVENTO".equalsIgnoreCase(item.getCategory())) {
            holder.binding.tvCategory.setTextColor(colorPrimary);
            holder.binding.viewAccent.setBackgroundColor(colorPrimary);
        } else {
            holder.binding.tvCategory.setTextColor(colorTeal);
            holder.binding.viewAccent.setBackgroundColor(colorTeal);
        }
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ItemNewsBinding binding;

        public ViewHolder(ItemNewsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
