package com.gemini.automation.backend;

import com.gemini.exception.GeminiTestRuntimeException;
import com.gemini.helpers.rest.ResponseEntity;
import com.gemini.util.TestUtil;
import com.gemini.util.components.GaugeTable;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.datastore.ScenarioDataStore;
import org.junit.jupiter.api.Assertions;

import java.util.Objects;

public class BackendSteps {
    private final BackendPage backendPage;

    public BackendSteps() {
        backendPage = new BackendPage(TestUtil.getInstance());
    }

    @Step("Register user for login using api service <table>")
    public void registerUserOnService(Table table) {
        GaugeTable registrationDetails = new GaugeTable(table);
        ResponseEntity responseEntity = backendPage.registerUserUsingService(registrationDetails);
        Assertions.assertEquals(201, responseEntity.getCode());
    }

    @Step("Register user for login using api service skip if exist <table>")
    public void registerUserOnServiceSkip(Table table) {
        GaugeTable registrationDetails = new GaugeTable(table);
        ResponseEntity responseEntity = backendPage.registerUserUsingService(registrationDetails);
        if (responseEntity.getCode() == 201) {
            Assertions.assertEquals(201, responseEntity.getCode());
        } else if (responseEntity.getCode() == 422) {
            Assertions.assertEquals(422, responseEntity.getCode());
        } else {
            Assertions.fail("Failed to validate accepted code of 201/422 code received from server was : " + responseEntity.getCode() + "with message : " + responseEntity.getResponse());
        }

    }

    @Step("Authenticate user with username <username> and password <password>")
    public void authenticateUserOnService(String username, String password) {
        ResponseEntity responseEntity = backendPage.authorizeUserUsingService(username, password);
        String token = EntityProcessor.getToken(responseEntity);
        Assertions.assertNotNull(token);
        ScenarioDataStore.put("token", token);
        ScenarioDataStore.put("username", username);
    }

    @Step("Create task using service <table>")
    public void createTaskWithTitleUsingService(Table table) {
        GaugeTable taskDetails = new GaugeTable(table);
        String token = (String) ScenarioDataStore.get("token");
        if (Objects.isNull(token)) {
            throw new GeminiTestRuntimeException("No authentication to run request", new Throwable("No authentication to run request"));
        }
        ResponseEntity responseEntity = backendPage.createTaskUsingService(taskDetails, token);
        Assertions.assertEquals(201, responseEntity.getCode());
    }

    @Step("Update task with details using service <table>")
    public void updateTaskUsingService(Table table) {
        GaugeTable updateTaskDetails = new GaugeTable(table);
        String token = (String) ScenarioDataStore.get("token");
        if (Objects.isNull(token)) {
            throw new GeminiTestRuntimeException("No authentication to run request", new Throwable("No authentication to run request"));
        }
        String title = (String) ScenarioDataStore.get("title");
        int id = backendPage.getIdUsingTitle(title, token);
        if(id == -1){
            Assertions.fail("Failed to retrieve task with title : " + title);
        }
        ResponseEntity responseEntity = backendPage.updateTaskUsingService(updateTaskDetails, token, id);
        Assertions.assertEquals(200, responseEntity.getCode());
    }

    @Step("Get all users using service")
    public void getAllUsersUsingService() {
        String token = (String) ScenarioDataStore.get("token");
        if (Objects.isNull(token)) {
            throw new GeminiTestRuntimeException("No authentication to run request", new Throwable("No authentication to run request"));
        }
        ResponseEntity responseEntity = backendPage.getAllUsers(token);
        ScenarioDataStore.put("users", responseEntity);
    }

    @Step("Validate current user is unauthorized for get all users request")
    public void validateCurrentUserIsUnauthorized() {
        ResponseEntity responseEntity = (ResponseEntity) ScenarioDataStore.get("users");
        Assertions.assertEquals(403, responseEntity.getCode());
    }
}
