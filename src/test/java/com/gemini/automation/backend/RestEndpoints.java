package com.gemini.automation.backend;

public class RestEndpoints {
    private static final String SERVICE_PREFIX = "/api";

    public static String getRegistration() {
        return createEndpoint("/auth/register");
    }

    public static String getAuthorization() {
        return createEndpoint("/auth");
    }

    public static String getTodos() {
        return createEndpoint("/todos");
    }

    public static String getTodo(int id) {
        return createEndpoint("/todos/" + id);
    }

    public static String getUsers() {
        return createEndpoint("/users/");
    }

    public static String createEndpoint(String service) {
        return String.format("%s%s", SERVICE_PREFIX, service);
    }
}
