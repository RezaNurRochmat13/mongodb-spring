package com.backend.mongodb.service;

import com.backend.mongodb.entity.Activity;
import com.backend.mongodb.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityRepository activityRepository;

    @Override
    public List<Activity> findAllActivities() {
        return activityRepository.findAll();
    }

    @Override
    public Activity createActivity(Activity payload) {
        payload.setCreatedAt(new Date());
        payload.setUpdatedAt(new Date());

        return activityRepository.save(payload);
    }
}
