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

    @PostMapping("/create")
    public ResponseEntity<Group> createGroup(@RequestBody GroupRequest request) {
        Group createdGroup = GroupRepository.addNewGroup(request.title);
        return ResponseEntity.ok(createdGroup);
    }

    @GetMapping("/get")
    public ResponseEntity<Group> getGroup (@RequestParam("id") long id){
        Group foundGroup = GroupRepository.findGroupById(id);
        return ResponseEntity.ok(foundGroup);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Group>> getListOfGroups() {
        return ResponseEntity.ok(GroupRepository.getListOfGroups());
    }

    @PutMapping("/edit")
    public ResponseEntity<Group> editGroup (@RequestBody GroupRequest request){
        return ResponseEntity.ok(GroupRepository.editGroup(request.id, request.title));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<List<Group>> deleteGroup (@RequestParam("id") long id){
        if (GroupRepository.deleteGroup(id)){
            return ResponseEntity.ok(GroupRepository.getListOfGroups());
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Group doesn't exist");
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
