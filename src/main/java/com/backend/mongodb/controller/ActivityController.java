package com.backend.mongodb.controller;

import com.backend.mongodb.entity.Activity;
import com.backend.mongodb.service.ActivityServiceImpl;
import com.backend.mongodb.util.BaseResponseList;
import com.backend.mongodb.util.BaseResponseSingle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class ActivityController {
    @Autowired
    private ActivityServiceImpl activityService;

    @Autowired
    private BaseResponseList baseResponseList;

    @Autowired
    private BaseResponseSingle baseResponseSingle;

    @GetMapping("/activities")
    public ResponseEntity<Object> getAllActivities(@RequestParam(defaultValue = "0", name = "page") Integer page,
                                                   @RequestParam(defaultValue = "10", name = "size") Integer size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Activity> activityPaging = activityService.findAllActivities(pageable);

        baseResponseList.setCount(activityPaging.getSize());
        baseResponseList.setTotal(activityPaging.getTotalElements());
        baseResponseList.setData(activityPaging.getContent());

        return new ResponseEntity<>(baseResponseList, HttpStatus.OK);
    }

    @GetMapping("/activities/{id}")
    public ResponseEntity<Object> getDetailActivities(@PathVariable String id) {
        baseResponseSingle.setData(activityService.detailActivityById(id));

        return new ResponseEntity<>(baseResponseSingle, HttpStatus.OK);
    }

    @PostMapping("/activities")
    public ResponseEntity<Object> createNewActivity(@RequestBody Activity payload) {
        return new ResponseEntity<>(activityService.createActivity(payload), HttpStatus.CREATED);
    }

    @PutMapping("/activities/{id}")
    public ResponseEntity<Object> updateActivity(@RequestBody Activity payload,
                                                 @PathVariable String id) {
        baseResponseSingle.setData(activityService.updateActivity(id, payload));

        return new ResponseEntity<>(baseResponseSingle, HttpStatus.OK);
    }

    @DeleteMapping("/activities/{id}")
    public ResponseEntity<Object> deleteActivity(@PathVariable String id) {
        activityService.deleteActivity(id);

        return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
    }

}
