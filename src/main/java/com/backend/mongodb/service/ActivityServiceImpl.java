package com.backend.mongodb.service;

import com.backend.mongodb.entity.Activity;
import com.backend.mongodb.repository.ActivityRepository;
import com.backend.mongodb.util.ResourceNotFoundException;
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
    public Activity detailActivityById(String id) {
        return activityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found id : " + id));
    }

    @Override
    public Activity createActivity(Activity payload) {
        payload.setCreatedAt(new Date());
        payload.setUpdatedAt(new Date());

        return activityRepository.save(payload);
    }
}
