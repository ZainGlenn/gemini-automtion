package com.gemini.automation.backend;

import com.gemini.automation.backend.model.get.TodoResponseModel;
import com.gemini.exception.GeminiTestRuntimeException;
import com.gemini.helpers.rest.ResponseEntity;
import com.gemini.helpers.rest.RestClient;
import com.gemini.util.TestUtil;
import com.gemini.util.components.GaugeTable;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

public class BackendPage {
    private final RestClient client;

    public BackendPage(TestUtil testUtils) {
        String url = testUtils.getProperty("api.url");
        this.client = new RestClient(url);
    }

    public ResponseEntity registerUserUsingService(GaugeTable registrationDetails) {
        String endpoint = RestEndpoints.getRegistration();
        String payload = EntityProcessor.createRegistrationJsonPayload(registrationDetails);
        try {
            return client.apiPost(endpoint, payload);
        } catch (IOException e) {
            throw new GeminiTestRuntimeException("Failed to send request - " + endpoint, new Throwable(e.getMessage()));
        }
    }

    public ResponseEntity authorizeUserUsingService(String username, String password) {
        String endpoint = RestEndpoints.getAuthorization();
        String payload = EntityProcessor.createLoginJsonPayload(username, password);
        try {
            return client.apiPost(endpoint, payload);
        } catch (IOException e) {
            throw new GeminiTestRuntimeException("Failed to send request - " + endpoint, new Throwable(e.getMessage()));
        }
    }

    public ResponseEntity createTaskUsingService(GaugeTable taskDetails, String token) {
        String endpoint = RestEndpoints.getTodos();
        String payload = EntityProcessor.createTaskJsonPayload(taskDetails);
        try {
            return client.apiPost(endpoint, payload, token);
        } catch (IOException e) {
            throw new GeminiTestRuntimeException("Failed to send request - " + endpoint, new Throwable(e.getMessage()));
        }

    }

    public ResponseEntity updateTaskUsingService(GaugeTable taskDetails, String token, int id) {
        String endpoint = RestEndpoints.getTodo(id);
        String payload = EntityProcessor.updateTaskJsonPayload(taskDetails);
        try {
            return client.apiPut(endpoint, payload, token);
        } catch (IOException e) {
            throw new GeminiTestRuntimeException("Failed to send request - " + endpoint, new Throwable(e.getMessage()));
        }
    }

    public int getIdUsingTitle(String title, String token) {
        String endpoint = RestEndpoints.getTodos();
        try {
            ResponseEntity responseEntity = client.apiGet(endpoint, token);
            TodoResponseModel[] tasks = EntityProcessor.getAllTasks(responseEntity);
            if(tasks.length == 0){
                return -1;
            }
            Optional<TodoResponseModel> taskOptional = Arrays.stream(tasks).filter(e -> e.getTitle().equals(title)).findFirst();
            return taskOptional.isEmpty() ? -1 : taskOptional.get().getId();
        } catch (IOException e) {
            throw new GeminiTestRuntimeException("Failed to send request - " + endpoint, new Throwable(e.getMessage()));
        }
    }

    public ResponseEntity getAllUsers(String token) {
        String endpoint = RestEndpoints.getUsers();
        try {
            return client.apiGet(endpoint, token);
        } catch (IOException e) {
            throw new GeminiTestRuntimeException("Failed to send request - " + endpoint, new Throwable(e.getMessage()));
        }
    }
}
