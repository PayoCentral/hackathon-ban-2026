package com.buap.arpavisos;

public class NewsItem {
    private String title;
    private String date;
    private String desc;
    private String category;
    private int imageResId;

    public NewsItem(String title, String date, String desc, String category) {
        this(title, date, desc, category, 0);
    }

    public NewsItem(String title, String date, String desc, String category, int imageResId) {
        this.title = title;
        this.date = date;
        this.desc = desc;
        this.category = category;
        this.imageResId = imageResId;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getDesc() {
        return desc;
    }

    public String getCategory() {
        return category;
    }

    public int getImageResId() {
        return imageResId;
    }
}
