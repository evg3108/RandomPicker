package com.evg3108.randompicker.controller;

import com.evg3108.randompicker.model.Group;
import com.evg3108.randompicker.repository.GroupRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {

    List<Group> groups = GroupRepository.getListOfGroups();

    @PostMapping("/create")
    public ResponseEntity<Group> createGroup(@RequestBody GroupRequest request) {
        Group createdGroup = new Group(request.title);
        groups.add(createdGroup);
        return ResponseEntity.ok(createdGroup);
    }

    @GetMapping("/get")
    public ResponseEntity<Group> getGroupById(@RequestParam("id") int id) {
        System.out.println(id);
        System.out.println(groups);
        Group foundGroup = groups.get(id);
        return ResponseEntity.ok(foundGroup);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Group>> getListOfAllGroups() {
        return ResponseEntity.ok(groups);
    }

    @PutMapping("/edit")
    public ResponseEntity<Group> editGroup(@RequestBody GroupRequest request) {
        Group foundGroup;
        try {
            foundGroup = groups.get(request.id);
            foundGroup.setTitle(request.title);
        } catch (IndexOutOfBoundsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Group with id=" + request.id + " not found");
        }
        return ResponseEntity.ok(foundGroup);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<List<Group>> deleteGroup(@RequestParam("id") int id) {
        try {
            groups.remove(id);
        } catch (IndexOutOfBoundsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Group with id=" + id + " not found");
        }
        return ResponseEntity.ok(groups);
    }

    public static class GroupRequest {
        private int id;
        private String title;
    }


}
