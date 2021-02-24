package com.gemini.automation.backend;

import com.gemini.automation.backend.model.get.AuthResponseModel;
import com.gemini.automation.backend.model.get.TodoResponseModel;
import com.gemini.automation.backend.model.post.LoginModel;
import com.gemini.automation.backend.model.post.RegistrationModel;
import com.gemini.automation.backend.model.post.TaskModel;
import com.gemini.exception.GeminiTestRuntimeException;
import com.gemini.helpers.rest.ResponseEntity;
import com.gemini.util.DateUtils;
import com.gemini.util.Utils;
import com.gemini.util.components.GaugeTable;
import com.google.gson.Gson;
import com.thoughtworks.gauge.datastore.ScenarioDataStore;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class EntityProcessor {
    private static final Gson gson = new Gson();
    private static final Logger logger = LogManager.getLogger(EntityProcessor.class);

    private EntityProcessor() {
    }

    public static String createRegistrationJsonPayload(GaugeTable registrationDetails) {
        String email = registrationDetails.getValue("email");
        String password = registrationDetails.getValue("password");

        RegistrationModel registrationModel = new RegistrationModel(email, password);
        return gson.toJson(registrationModel);
    }

    public static String createLoginJsonPayload(String email, String password) {
        LoginModel loginModel = new LoginModel(email, password);
        return gson.toJson(loginModel);
    }

    public static String createTaskJsonPayload(GaugeTable taskDetails) {
        String title = taskDetails.getValue("title");
        String desc = taskDetails.getValue("description");
        String isComplete = taskDetails.getValue("is complete");

        if (title.contains("username")) {
            title = title.replaceAll("username", (String) ScenarioDataStore.get("username"));
        }

        if (title.contains("today")) {
            title = title.replaceAll("today", DateUtils.getDateNow());
        }

        ScenarioDataStore.put("title", title);

        boolean needsComplete = Utils.yesNoToBool(isComplete);
        TaskModel taskModel = new TaskModel(title, desc, needsComplete);
        return gson.toJson(taskModel);
    }

    public static String updateTaskJsonPayload(GaugeTable taskDetails) {
        String desc = taskDetails.getValue("description");
        String isComplete = taskDetails.getValue("is complete");

        boolean needsComplete = Utils.yesNoToBool(isComplete);
        TaskModel taskModel = new TaskModel(null, desc, needsComplete);
        return gson.toJson(taskModel);
    }


    public static String getToken(ResponseEntity responseEntity) {
        if (!responseEntity.isSuccessful()) {
            logger.error("Failed verify request was sent successfully -> " + responseEntity.getResponse());
            throw new GeminiTestRuntimeException("Failed to verify request was successful", new Throwable("Failed to verify request was successful"));
        }

        AuthResponseModel authSchema = gson.fromJson(responseEntity.getResponse(), AuthResponseModel.class);
        return authSchema.getToken();
    }

    public static TodoResponseModel[] getAllTasks(ResponseEntity responseEntity) {
        if (!responseEntity.isSuccessful()) {
            logger.error("Failed verify request was sent successfully -> " + responseEntity.getResponse());
            throw new GeminiTestRuntimeException("Failed to verify request was successful", new Throwable("Failed to verify request was successful"));
        }

        return gson.fromJson(responseEntity.getResponse(), TodoResponseModel[].class);
    }

}
