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
@RequestMapping("/entry")
public class EntryController {

    List<Group> groups = GroupRepository.getListOfGroups();

    @PostMapping("/create")
    public ResponseEntity<Entry> createEntry(@RequestBody EntryRequest request) {
        Group foundGroup;
        Entry createdEntry = new Entry(request.title, request.groupID);
        try{
            foundGroup = groups.get(request.groupID);
            foundGroup.addEntry(createdEntry);
        } catch (IndexOutOfBoundsException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Requested group not found");
        }

        return ResponseEntity.ok(createdEntry);
    }

    @GetMapping()
    public ResponseEntity<Entry> getEntry (@RequestParam("id") int id,
                                           @RequestParam("groupID") int groupID){
        Group foundGroup;
        Entry foundEntry;
        try{
            foundGroup = groups.get(groupID);
            try {
                foundEntry = foundGroup.getEntries().get(id);
            } catch (IndexOutOfBoundsException e){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Requested entry not found");
            }
        } catch (IndexOutOfBoundsException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Requested group not found");
        }
        return ResponseEntity.ok(foundEntry);
    }

    @PutMapping("/edit")
    public ResponseEntity<Entry> editEntry (@RequestBody EntryRequest request){
        Group foundGroup;
        Entry foundEntry;
        try{
            foundGroup = groups.get(request.groupID);
            try{
                foundEntry = foundGroup.getEntries().get(request.id);
                foundEntry.setTitle(request.title);
            } catch(IndexOutOfBoundsException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Entry with id=" + request.id + " not found");
            }
        } catch (IndexOutOfBoundsException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Group with id=" + request.groupID + " not found");
        }
        return ResponseEntity.ok(foundEntry);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Group> deleteEntry (@RequestBody EntryRequest request){
        Group foundGroup;
        try{
            foundGroup = groups.get(request.groupID);
            try{
                foundGroup.getEntries().remove(request.id);
            } catch (IndexOutOfBoundsException e){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Entry with id=" + request.id + " not found");
            }
        } catch (IndexOutOfBoundsException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Group with id=" + request.groupID + " not found");
        }
        return ResponseEntity.ok(foundGroup);
    }

    public static class EntryRequest {

        private int id;

        private String title;

        private int groupID;

        public String getTitle() {
            return title;
        }

    }
}
