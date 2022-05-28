package com.evg3108.randompicker.model;

import java.util.ArrayList;
import java.util.List;

public class Group {

    private long id;
    private String title;
    private List<Entry> entries = new ArrayList<>();

    public Group(){}

    public Group(String title){
        this.title = title;
    }

    public Group(long id, String title){
        this.id = id;
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

    public void setId(long id) {
        this.id = id;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    public void addEntry(Entry entry){
        entries.add(entry);
    }

    public Entry findEntryById(long id){
        for(Entry entry : entries){
            if(entry.getId()==id){
                return entry;
            }
        }
        return null;
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
