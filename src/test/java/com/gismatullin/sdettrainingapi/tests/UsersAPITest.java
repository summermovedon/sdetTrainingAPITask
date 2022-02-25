package com.gismatullin.sdettrainingapi.tests;

import static com.gismatullin.sdettrainingapi.steps.Steps.getUser;
import static com.gismatullin.sdettrainingapi.steps.Steps.getUserWithPagination;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.gismatullin.sdettrainingapi.model.User;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UsersAPITest {

    @Test
    @DisplayName("Test UsersAPI without pagination")
    public void simpleUserTest() {
        String firstName = "George";
        String secondName = "Bluth";
        String expectedEmail = "george.bluth@reqres.in";
        User user = getUser(firstName, secondName);
        String actualEmail = user.getEmail();
        assertEquals(expectedEmail, actualEmail);
    }

    @Test
    @DisplayName("Test UsersAPI with searching for pages")
    public void paginationUserTest() {
        String firstName = "Michael";
        String secondName = "Lawson";
        String expectedEmail = "michael.lawson@reqres.in";
        User user = getUserWithPagination(firstName, secondName);
        String actualEmail = user.getEmail();
        assertEquals(expectedEmail, actualEmail);
    }

}
