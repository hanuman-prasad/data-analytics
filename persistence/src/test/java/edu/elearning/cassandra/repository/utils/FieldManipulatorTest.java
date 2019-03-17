package edu.elearning.cassandra.repository.utils;

import edu.elearning.se.User;
import edu.elearning.se.UserWebsite;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class FieldManipulatorTest {

    @Test
    public void getFieldValue() {
        User user = User.builder()
                .id("123")
                .displayName("user")
                .upVotes("334")
                .views("4321")
                .creationDate(LocalDateTime.now())
                .lastAccessDate(LocalDateTime.now())
                .userWebsite(UserWebsite.HARDWARE)
                .build();

        Object views = FieldManipulator.getFieldValue(user, "upvotes");
        assertEquals("4321", views);
    }
}