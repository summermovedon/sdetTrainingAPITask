package com.gismatullin.sdettrainingapi.steps;

import static io.restassured.RestAssured.given;

import java.util.Optional;

import com.gismatullin.sdettrainingapi.model.User;
import com.gismatullin.sdettrainingapi.model.UsersDataResponse;

import io.restassured.http.ContentType;

public class Steps {

    private Steps() {}
    
    private static final String BASE_URL = "https://reqres.in/api/users";

    public static User getUser(String firstName, String secondName) {
        UsersDataResponse response = sendUsersRequest();
        Optional<User> userOptional = response.getUsers().stream()
            .filter(u -> u.getFirstName().equals(firstName) && u.getLastName().equals(secondName))
            .findFirst();
        return userOptional.get();
    }

    public static User getUserWithPagination(String firstName, String secondName) {
        int page = 1;
        UsersDataResponse response = sendUsersRequest(page);
        int totalPages = response.getTotalPages();
        Optional<User> userOptional = Optional.empty();
        while (page <= totalPages) {
            userOptional = response.getUsers().stream()
                .filter(u -> u.getFirstName().equals(firstName) && u.getLastName().equals(secondName))
                .findFirst();
            if (userOptional.isPresent() || page == totalPages) {
                return userOptional.get();
            }
            response = sendUsersRequest(++page);
        }
        return userOptional.get();
    }

    private static UsersDataResponse sendUsersRequest() {
        return given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
            .when()
                .get()
            .then()
                .statusCode(200)
                .extract()
                .as(UsersDataResponse.class);
    }

    private static UsersDataResponse sendUsersRequest(int page) {
        return given()
                .baseUri(BASE_URL)
                .param("page", page)
                .contentType(ContentType.JSON)
            .when()
                .get()
            .then()
                .statusCode(200)
                .extract()
                .as(UsersDataResponse.class);
    }
    
}
