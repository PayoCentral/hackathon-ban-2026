package com.buap.arpavisos;

public class ClubItem {
    private String title;
    private String category;
    private String status; // "Activa", "En curso", "Pasada"
    private String desc;
    private String schedule;
    private int imageResId;

    public ClubItem(String title, String category, String status, String desc, String schedule, int imageResId) {
        this.title = title;
        this.category = category;
        this.status = status;
        this.desc = desc;
        this.schedule = schedule;
        this.imageResId = imageResId;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }

    public String getSchedule() {
        return schedule;
    }

    public int getImageResId() {
        return imageResId;
    }
}
