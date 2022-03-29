package com.backend.mongodb.controller;

import com.backend.mongodb.entity.Activity;
import com.backend.mongodb.service.ActivityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class ActivityController {
    @Autowired
    private ActivityServiceImpl activityService;

    @GetMapping("/activities")
    public ResponseEntity<Object> getAllActivities() {
        return new ResponseEntity<>(activityService.findAllActivities(), HttpStatus.OK);
    }

    @GetMapping("/activities/{id}")
    public ResponseEntity<Object> getDetailActivities(@PathVariable String id) {
        return new ResponseEntity<>(activityService.detailActivityById(id), HttpStatus.OK);
    }

    @PostMapping("/activities")
    public ResponseEntity<Object> createNewActivity(@RequestBody Activity payload) {
        return new ResponseEntity<>(activityService.createActivity(payload), HttpStatus.CREATED);
    }
}
