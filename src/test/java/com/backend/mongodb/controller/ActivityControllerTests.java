package com.backend.mongodb.controller;

import com.backend.mongodb.MongoDBApplicationTests;
import com.backend.mongodb.repository.ActivityRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ActivityControllerTests extends MongoDBApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ActivityRepository activityRepository;

    @Test
    public void testGetAllActivitiesWithPagination() {}

    @Test
    public void testGetAllActivitiesWithoutPagination() {}

    @Test
    public void testGetDetailActivitiesWithValidIds() {}

    @Test
    public void testGetDetailActivitiesWithInvalidIds() {}

    @Test
    public void testCreateNewActivityWithPayload() {}

    @Test
    public void testCreateNewActivityWithoutPayload() {}

    @Test
    public void testUpdateActivityWithValidIdAndValidPayload() {}

    @Test
    public void testUpdateActivityWithInvalidIdAndValidPayload() {}

    @Test
    public void testUpdateActivityWithValidIdAndInvalidPayload() {}

    @Test
    public void testDeleteActivityWithValidIds() {}

    @Test
    public void testDeleteActivityWithInvalidIds() {}
}