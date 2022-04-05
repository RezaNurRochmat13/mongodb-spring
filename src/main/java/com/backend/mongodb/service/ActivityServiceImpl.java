package com.backend.mongodb.service;

import com.backend.mongodb.entity.Activity;
import com.backend.mongodb.repository.ActivityRepository;
import com.backend.mongodb.util.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityRepository activityRepository;

    @Override
    public Page<Activity> findAllActivities(Pageable pageable) {
        return activityRepository.findAll(pageable);
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

    @Override
    public Activity updateActivity(String id, Activity payload) {
        Activity activity = activityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found id : " + id));

        activity.setActivityName(payload.getActivityName());
        activity.setActivityDescription(payload.getActivityDescription());
        activity.setAgent(payload.getAgent());

        return activityRepository.save(activity);
    }

    @Override
    public void deleteActivity(String id) {
        Activity activity = activityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found id : " + id));

        activityRepository.delete(activity);
    }
}
