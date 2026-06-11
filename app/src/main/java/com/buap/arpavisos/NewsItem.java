package com.buap.arpavisos;

public class NewsItem {
    private String title;
    private String date;
    private String desc;
    private String category;

    public NewsItem(String title, String date, String desc, String category) {
        this.title = title;
        this.date = date;
        this.desc = desc;
        this.category = category;
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
}
