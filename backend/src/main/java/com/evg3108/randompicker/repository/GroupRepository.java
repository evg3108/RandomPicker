package com.evg3108.randompicker.repository;

import com.evg3108.randompicker.model.Group;

import java.util.ArrayList;
import java.util.List;

public class GroupRepository {

    static List<Group> groups = new ArrayList<>();

    public static List<Group> getListOfGroups(){
        return  groups;
    }

}
