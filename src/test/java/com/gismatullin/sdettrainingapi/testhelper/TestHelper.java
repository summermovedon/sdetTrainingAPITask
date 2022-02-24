package com.gismatullin.sdettrainingapi.testhelper;

import static io.restassured.RestAssured.given;

import java.util.Optional;

import com.gismatullin.sdettrainingapi.model.User;
import com.gismatullin.sdettrainingapi.model.UsersDataResponse;

import io.restassured.http.ContentType;

public class TestHelper {

    private TestHelper() {}

    public static User searchUser(String firstName, String secondName) {
        int page = 1;
        UsersDataResponse response = sendGetUsersRequest(page);
        int totalPages = response.getTotalPages();
        Optional<User> userOptional = Optional.empty();
        while (page <= totalPages) {
            userOptional = response.getUsers().stream()
                .filter(u -> u.getFirstName().equals(firstName) && u.getLastName().equals(secondName))
                .findFirst();
            if (userOptional.isPresent() || page == totalPages) {
                return userOptional.get();
            }
            response = sendGetUsersRequest(++page);
        }
        return userOptional.get();
    }

    private static UsersDataResponse sendGetUsersRequest(int page) {
        return given()
                .baseUri("https://reqres.in/api/users")
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
