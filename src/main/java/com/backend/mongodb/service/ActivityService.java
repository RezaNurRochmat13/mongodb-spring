package com.backend.mongodb.service;

import com.backend.mongodb.entity.Activity;

import java.util.List;

public interface ActivityService {
    List<Activity> findAllActivities();
    Activity detailActivityById(String id);
    Activity createActivity(Activity payload);
    Activity updateActivity(String id, Activity payload);
}
