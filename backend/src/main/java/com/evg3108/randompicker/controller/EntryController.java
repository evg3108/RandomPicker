package com.evg3108.randompicker.controller;

import com.evg3108.randompicker.model.Entry;
import com.evg3108.randompicker.model.Group;
import com.evg3108.randompicker.repository.GroupRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/entries")
public class EntryController {

    List<Group> groups = GroupRepository.getListOfGroups();

    @PostMapping("/create")
    public ResponseEntity<Entry> createEntry(@RequestBody EntryRequest request) {
        Group foundGroup = GroupRepository.findGroupById(request.groupID);
        Entry createdEntry = new Entry(request.title, request.groupID);
        try {
            foundGroup.addEntry(createdEntry);
            return ResponseEntity.ok(createdEntry);
        } catch (NullPointerException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Group not found");
        }
    }

    @GetMapping("/get")
    public ResponseEntity<Entry> getEntry(@RequestParam("id") long id,
                                          @RequestParam("groupID") long groupID) {
        Group foundGroup = GroupRepository.findGroupById(groupID);
        Entry foundEntry;
        try {
            foundEntry = foundGroup.findEntryById(id);
            return ResponseEntity.ok(foundEntry);
        } catch (NullPointerException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Entry not found");
        }
    }

    @PutMapping("/edit")
    public ResponseEntity<Entry> editEntry(@RequestBody EntryRequest request) {
        Group foundGroup = GroupRepository.findGroupById(request.groupID);
        Entry foundEntry;
        try {
            foundEntry = foundGroup.findEntryById(request.id);
            foundEntry.setTitle(request.title);
            return ResponseEntity.ok(foundEntry);
        } catch (NullPointerException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Entry not found");
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Group> deleteEntry(@RequestBody EntryRequest request) {
        Group foundGroup = GroupRepository.findGroupById(request.groupID);
        Entry entryToDelete;
        try {
            entryToDelete = foundGroup.findEntryById(request.id);
            foundGroup.getEntries().remove(entryToDelete);
            return ResponseEntity.ok(foundGroup);
        } catch (NullPointerException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Entry not found");
        }
    }

    public static class EntryRequest {

        private long id;
        private String title;
        private long groupID;

        public long getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public long getGroupID() {
            return groupID;
        }
    }
}
