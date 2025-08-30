package test.java.com.example;

import main.java.com.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubtaskTest {
    @Test
    void shouldCreateSubtaskWithEpicId() {
        Subtask subtask = new Subtask("Test Subtask", "Description", 1);
        Assertions.assertEquals(1, subtask.getEpicId());
    }

    @Test
    void shouldNotAllowNegativeEpicId() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Subtask("Test", "Desc", -1);
        });
    }
}