package com.buap.arpavisos;

public class PostItem {
    private String username;
    private String description;
    private int likesCount;
    private boolean isLiked;
    private String timeAgo;
    private int profileImageResId;
    private int postImageResId;

    public PostItem(String username, String description, int likesCount, boolean isLiked, String timeAgo, int profileImageResId, int postImageResId) {
        this.username = username;
        this.description = description;
        this.likesCount = likesCount;
        this.isLiked = isLiked;
        this.timeAgo = timeAgo;
        this.profileImageResId = profileImageResId;
        this.postImageResId = postImageResId;
    }

    public String getUsername() {
        return username;
    }

    public String getDescription() {
        return description;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public String getTimeAgo() {
        return timeAgo;
    }

    public int getProfileImageResId() {
        return profileImageResId;
    }

    public int getPostImageResId() {
        return postImageResId;
    }

    // Toggle me gusta status and updates likes count
    public void toggleLike() {
        isLiked = !isLiked;
        if (isLiked) {
            likesCount++;
        } else {
            likesCount--;
        }
    }
}
