package com.backend.mongodb.controller;

import com.backend.mongodb.MongoDBApplicationTests;
import com.backend.mongodb.entity.Activity;
import com.backend.mongodb.repository.ActivityRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@ActiveProfiles(profiles = "test")
public class ActivityControllerTests extends MongoDBApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ActivityRepository activityRepository;

    @Before
    public void setupData() throws Exception {
        List<Activity> activityList = new ArrayList<>(
                Arrays.asList(
                        new Activity("Insert DB", "", "Postman"),
                        new Activity("Update DB", "", "Postman"),
                        new Activity("DELETE DB", "", "Postman"),
                        new Activity("GET DATA DB", "", "Postman"),
                        new Activity("GET DETAIL DB", "", "Postman")
                )
        );

        activityRepository.saveAll(activityList);
    }

    @Test
    public void testGetAllActivitiesWithPagination() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v1/activities?page=0&size=10")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
                .perform(requestBuilder)
                .andReturn();

        JSONObject actual = new JSONObject(response.getResponse().getContentAsString());

        // Assertion
        assertEquals(200, response.getResponse().getStatus());
        assertEquals(10, actual.getJSONArray("data").length());
    }

    @Test
    public void testGetAllActivitiesWithoutPagination() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v1/activities")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
                .perform(requestBuilder)
                .andReturn();

        JSONObject actual = new JSONObject(response.getResponse().getContentAsString());

        // Assertion
        assertEquals(200, response.getResponse().getStatus());
        assertEquals(10, actual.getJSONArray("data").length());
    }

    @Test
    public void testGetDetailActivitiesWithValidIds() throws Exception {
        Activity activity = activityRepository.save(
                new Activity("Update Activity DB", "", "Postman")
        );

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v1/activities/" + activity.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
                .perform(requestBuilder)
                .andReturn();

        JSONObject actual = new JSONObject(response.getResponse().getContentAsString());

        // Assertion
        assertEquals(200, response.getResponse().getStatus());
        assertEquals("Update Activity DB", actual.getJSONObject("data").get("activity_name"));
    }

    @Test
    public void testGetDetailActivitiesWithInvalidIds() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v1/activities/" + new Random().nextLong())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
                .perform(requestBuilder)
                .andReturn();

        // Assertion
        assertEquals(404, response.getResponse().getStatus());
    }

    @Test
    public void testCreateNewActivityWithPayload() throws Exception {
        JSONObject payload = new JSONObject();
        payload.put("activity_name", "Insert DB");
        payload.put("activity_description", "");
        payload.put("agent", "Insomnia/Firefox");

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/v1/activities")
                .content(payload.toString())
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
                .perform(requestBuilder)
                .andReturn();

        // Assertion
        assertEquals(201, response.getResponse().getStatus());
    }

    @Test
    public void testCreateNewActivityWithoutPayload() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/v1/activities")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
                .perform(requestBuilder)
                .andReturn();

        // Assertion
        assertEquals(400, response.getResponse().getStatus());
    }

    @Test
    public void testUpdateActivityWithValidIdAndValidPayload() throws Exception {
        JSONObject payload = new JSONObject();
        payload.put("activity_name", "Delete Data DB");
        payload.put("activity_description", "");
        payload.put("agent", "Insomnia/Firefox");

        Activity activity = activityRepository.save(
                new Activity("Update Activity DB", "", "Postman")
        );

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/v1/activities/" + activity.getId())
                .content(payload.toString())
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
                .perform(requestBuilder)
                .andReturn();

        JSONObject actual = new JSONObject(response.getResponse().getContentAsString());

        // Assertion
        assertEquals(200, response.getResponse().getStatus());
        assertEquals("Delete Data DB", actual.getJSONObject("data").get("activity_name"));
    }

    @Test
    public void testUpdateActivityWithInvalidIdAndValidPayload() throws Exception {
        JSONObject payload = new JSONObject();
        payload.put("activity_name", "Delete Data DB");
        payload.put("activity_description", "");
        payload.put("agent", "Insomnia/Firefox");

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/v1/activities/ssssasasas")
                .content(payload.toString())
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
                .perform(requestBuilder)
                .andReturn();

        // Assertion
        assertEquals(404, response.getResponse().getStatus());
    }

    @Test
    public void testUpdateActivityWithValidIdAndInvalidPayload() throws Exception {
        Activity activity = activityRepository.save(
                new Activity("Update Activity DB", "", "Postman")
        );

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/v1/activities/" + activity.getId())
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
                .perform(requestBuilder)
                .andReturn();

        // Assertion
        assertEquals(400, response.getResponse().getStatus());
    }

    @Test
    public void testDeleteActivityWithValidIds() throws Exception {
        Activity activity = activityRepository.save(
                new Activity("Update Activity DB", "", "Postman")
        );

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/v1/activities/" + activity.getId())
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
                .perform(requestBuilder)
                .andReturn();

        // Assertion
        assertEquals(204, response.getResponse().getStatus());
    }

    @Test
    public void testDeleteActivityWithInvalidIds() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/v1/activities/askansajnxsjcxs")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
                .perform(requestBuilder)
                .andReturn();

        // Assertion
        assertEquals(404, response.getResponse().getStatus());
    }
}