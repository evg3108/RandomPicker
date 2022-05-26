package com.evg3108.randompicker.controller;

import com.evg3108.randompicker.model.Group;
import com.evg3108.randompicker.repository.GroupRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {

    List<Group> groups = GroupRepository.getListOfGroups();

    @PostMapping("/create")
    public ResponseEntity<Group> createGroup(@RequestBody GroupRequest request) {
        Group createdGroup = new Group(request.title);
        GroupRepository.addNewGroup(createdGroup);
        return ResponseEntity.ok(createdGroup);
    }

    @GetMapping("/get")
    public ResponseEntity<Group> getGroup (@RequestParam("id") long id){
        Group foundGroup = GroupRepository.findGroupById(id);
        return ResponseEntity.ok(foundGroup);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Group>> getListOfGroups(){
        return ResponseEntity.ok(groups);
    }

    @PutMapping("/edit")
    public ResponseEntity<Group> editGroup (@RequestBody GroupRequest request){
        Group foundGroup = GroupRepository.findGroupById(request.id);
        try{
            foundGroup.setTitle(request.title);
            return ResponseEntity.ok(foundGroup);
        } catch (NullPointerException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Group not found");
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<List<Group>> deleteGroup (@RequestParam("id") long id){
        Group deletedGroup = GroupRepository.findGroupById(id);
        if(groups.remove(deletedGroup)){
            return ResponseEntity.ok(groups);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Group not found");
    }

    public static class GroupRequest {

        private long id;
        private String title;

        public String getTitle() {
            return title;
        }

        public long getId() {
            return id;
        }
    }


}
