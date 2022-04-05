package com.backend.mongodb.service;

import com.backend.mongodb.entity.Activity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ActivityService {
    Page<Activity> findAllActivities(Pageable pageable);
    Activity detailActivityById(String id);
    Activity createActivity(Activity payload);
    Activity updateActivity(String id, Activity payload);
    void deleteActivity(String id);
}
