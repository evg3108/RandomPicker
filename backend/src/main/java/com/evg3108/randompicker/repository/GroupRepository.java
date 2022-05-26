package com.evg3108.randompicker.repository;

import com.evg3108.randompicker.model.Group;

import java.util.ArrayList;
import java.util.List;

public class GroupRepository {

    private static List<Group> groups = new ArrayList<>();

    public static List<Group> getListOfGroups(){
        return  groups;
    }

    public static void addNewGroup(Group group){
        groups.add(group);
    }

    public static Group findGroupById(long id){
        for(Group group : groups){
            if(group.getId()==id){
                return group;
            }
        }
        return null;
    }


}
