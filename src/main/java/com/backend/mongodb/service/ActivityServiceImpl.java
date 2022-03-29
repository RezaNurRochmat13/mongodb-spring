package com.backend.mongodb.service;

import com.backend.mongodb.entity.Activity;
import com.backend.mongodb.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityRepository activityRepository;

    @Override
    public List<Activity> findAllActivities() {
        return activityRepository.findAll();
    }
}
