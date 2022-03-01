package com.gismatullin.sdettrainingapi.tests;

import static com.gismatullin.sdettrainingapi.steps.Steps.getUser;
import static com.gismatullin.sdettrainingapi.steps.Steps.getUserWithPagination;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.gismatullin.sdettrainingapi.model.User;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class UsersAPITest {

    @ParameterizedTest
    @DisplayName("Test UsersAPI without pagination")
    @CsvSource("George, Bluth, george.bluth@reqres.in")
    public void simpleUserTest(String firstName, String secondName, String expectedEmail) {
        User user = getUser(firstName, secondName);
        String actualEmail = user.getEmail();
        assertEquals(expectedEmail, actualEmail);
    }

    @ParameterizedTest
    @DisplayName("Test UsersAPI with searching for pages")
    @CsvSource("Michael, Lawson, michael.lawson@reqres.in")
    public void paginationUserTest(String firstName, String secondName, String expectedEmail) {
        User user = getUserWithPagination(firstName, secondName);
        String actualEmail = user.getEmail();
        assertEquals(expectedEmail, actualEmail);
    }

}
