package com.evg3108.randompicker.controller;

import com.evg3108.randompicker.model.Entry;
import com.evg3108.randompicker.model.Group;
import com.evg3108.randompicker.repository.EntryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/entries")
public class EntryController {

    @PostMapping("/create")
    public ResponseEntity<Void> createEntry(@RequestBody EntryRequest request) {
        if(EntryRepository.addNewEntry(request.getTitle(), request.getGroupID())){
            return ResponseEntity.ok(null);
        }
         throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/get")
    public ResponseEntity<Entry> getEntry(@RequestParam("id") long id) {
        return ResponseEntity.ok(EntryRepository.findEntryByID(id));
    }

    @PutMapping("/edit")
    public ResponseEntity<Entry> editEntry(@RequestBody EntryRequest request) {
        return ResponseEntity.ok(EntryRepository.editEntry(request.getId(), request.getTitle()));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Group> deleteEntry(@RequestBody EntryRequest request) {
        if(EntryRepository.deleteEntry(request.id)){
            return ResponseEntity.ok(null);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
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
