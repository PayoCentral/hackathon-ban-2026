package com.buap.arpavisos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.buap.arpavisos.databinding.ItemGalleryPostBinding;
import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    private final List<PostItem> postList;

    public GalleryAdapter(List<PostItem> postList) {
        this.postList = postList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemGalleryPostBinding binding = ItemGalleryPostBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PostItem item = postList.get(position);

        // Bind text info
        holder.binding.tvUsername.setText(item.getUsername());
        holder.binding.tvDescription.setText(item.getDescription());
        holder.binding.tvPostDate.setText(item.getTimeAgo());
        updateLikesText(holder, item);

        // Bind image resources
        holder.binding.imgProfile.setImageResource(item.getProfileImageResId());
        holder.binding.imgPost.setImageResource(item.getPostImageResId());

        // Update heart icon state
        holder.binding.btnLike.setImageResource(item.isLiked() ? 
                R.drawable.ic_heart_filled : R.drawable.ic_heart_outline);

        // Handle me gusta click listener
        holder.binding.btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.toggleLike();
                // Animate and update state
                holder.binding.btnLike.setImageResource(item.isLiked() ? 
                        R.drawable.ic_heart_filled : R.drawable.ic_heart_outline);
                updateLikesText(holder, item);
            }
        });

        // Add a nice premium double-tap to like on the post image
        holder.binding.imgPost.setOnClickListener(new View.OnClickListener() {
            private long lastClickTime = 0;
            @Override
            public void onClick(View v) {
                long clickTime = System.currentTimeMillis();
                if (clickTime - lastClickTime < 300) {
                    // Double tap detected
                    if (!item.isLiked()) {
                        item.toggleLike();
                        holder.binding.btnLike.setImageResource(R.drawable.ic_heart_filled);
                        updateLikesText(holder, item);
                    }
                }
                lastClickTime = clickTime;
            }
        });
    }

    private void updateLikesText(ViewHolder holder, PostItem item) {
        holder.binding.tvLikes.setText(item.getLikesCount() + " Me gustas");
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ItemGalleryPostBinding binding;

        public ViewHolder(ItemGalleryPostBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
