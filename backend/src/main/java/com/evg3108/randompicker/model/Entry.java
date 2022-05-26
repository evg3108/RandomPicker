package com.evg3108.randompicker.model;

public class Entry {

    private static long idCounter = 1;

    private long id;
    private long groupID;
    private String title;

    public Entry(String title, long groupID) {
        this.groupID = groupID;
        this.id = idCounter++;
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public long getGroupID() {
        return groupID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
