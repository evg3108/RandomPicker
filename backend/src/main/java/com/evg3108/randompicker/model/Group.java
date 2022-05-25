package com.evg3108.randompicker.model;

import com.evg3108.randompicker.model.Entry;
import java.util.ArrayList;
import java.util.List;

public class Group {

    private static long idCounter = 0;

    private long id;
    private String title;
    private List<Entry> entries = new ArrayList<>();

    public Group(String title) {
        this.id = idCounter++;
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void addEntry(Entry entry){
        entries.add(entry);
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", entries=" + entries +
                '}';
    }
}
