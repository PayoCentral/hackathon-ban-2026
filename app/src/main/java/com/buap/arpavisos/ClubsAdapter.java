package com.buap.arpavisos;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.buap.arpavisos.databinding.ItemClubBinding;
import java.util.List;

public class ClubsAdapter extends RecyclerView.Adapter<ClubsAdapter.ViewHolder> {

    public interface OnClubClickListener {
        void onClubClick(ClubItem club);
    }

    private final List<ClubItem> clubList;
    private final OnClubClickListener clickListener;

    public ClubsAdapter(List<ClubItem> clubList, OnClubClickListener clickListener) {
        this.clubList = clubList;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemClubBinding binding = ItemClubBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ClubItem item = clubList.get(position);
        holder.binding.tvClubTitle.setText(item.getTitle());
        holder.binding.tvClubCategory.setText(item.getCategory());
        holder.binding.tvClubDesc.setText(item.getDesc());
        holder.binding.tvClubStatus.setText(item.getStatus());

        // Get context
        android.content.Context context = holder.itemView.getContext();

        // Dynamically color state badge
        int statusColor;
        if ("Activa".equalsIgnoreCase(item.getStatus())) {
            statusColor = ContextCompat.getColor(context, R.color.statusActiva);
        } else if ("En curso".equalsIgnoreCase(item.getStatus())) {
            statusColor = ContextCompat.getColor(context, R.color.statusEnCurso);
        } else {
            statusColor = ContextCompat.getColor(context, R.color.statusPasada);
        }

        // Apply tint color to drawable background of status chip
        Drawable background = holder.binding.tvClubStatus.getBackground();
        if (background != null) {
            background.setColorFilter(statusColor, PorterDuff.Mode.SRC_IN);
        }

        // Set click listener
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null) {
                    clickListener.onClubClick(item);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return clubList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ItemClubBinding binding;

        public ViewHolder(ItemClubBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
