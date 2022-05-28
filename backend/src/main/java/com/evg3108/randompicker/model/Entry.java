package com.evg3108.randompicker.model;

public class Entry {

    private long id;
    private long groupId;
    private String title;

    public Entry(){}

    public Entry(String title, long groupId) {
        this.groupId = groupId;
        this.title = title;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
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
