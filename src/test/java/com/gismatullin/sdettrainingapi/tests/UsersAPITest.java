package com.gismatullin.sdettrainingapi.tests;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static com.gismatullin.sdettrainingapi.testhelper.TestHelper.searchUser;

import java.util.Optional;

import com.gismatullin.sdettrainingapi.model.User;
import com.gismatullin.sdettrainingapi.model.UsersDataResponse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.restassured.http.ContentType;

public class UsersAPITest {

    @Test
    @DisplayName("Test user without searching")
    public void simpleUserTest() {
        String firstName = "George";
        String secondName = "Bluth";
        String expectedEmail = "george.bluth@reqres.in";
        UsersDataResponse response = given()
            .baseUri("https://reqres.in/api/users")
            .contentType(ContentType.JSON)
        .when()
            .get()
        .then()
            .statusCode(200)
            .extract()
            .as(UsersDataResponse.class);
        Optional<User> userOptional = response.getUsers().stream()
            .filter(u -> u.getFirstName().equals(firstName) && u.getLastName().equals(secondName))
            .findFirst();
        String actualEmail = userOptional.get().getEmail();
        assertEquals(expectedEmail, actualEmail);
    }

    @Test
    @DisplayName("Test user with searching for pages")
    public void paginationUserTest() {
        String firstName = "Michael";
        String secondName = "Lawson";
        String expectedEmail = "michael.lawson@reqres.in";
        User user = searchUser(firstName, secondName);
        String actualEmail = user.getEmail();
        assertEquals(expectedEmail, actualEmail);
    }

}
