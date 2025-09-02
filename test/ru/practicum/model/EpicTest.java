package ru.practicum.model;

import main.ru.practicum.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EpicTest {
    @Test
    void shouldCreateEpicWithEmptySubtasks() {
        Epic epic = new Epic("Test Epic", "Description");
        assertNotNull(epic.getSubtaskIds());
        Assertions.assertTrue(epic.getSubtaskIds().isEmpty());
    }

    @Test
    void shouldAddAndRemoveSubtaskIds() {
        Epic epic = new Epic("Test Epic", "Description");
        epic.addSubtaskId(1);
        epic.addSubtaskId(2);

        Assertions.assertEquals(2, epic.getSubtaskIds().size());
        Assertions.assertTrue(epic.getSubtaskIds().contains(1));
        Assertions.assertTrue(epic.getSubtaskIds().contains(2));

        epic.removeSubtaskId(1);
        Assertions.assertEquals(1, epic.getSubtaskIds().size());
        Assertions.assertFalse(epic.getSubtaskIds().contains(1));
    }

    @Test
    void shouldReturnCopyOfSubtaskIds() {
        Epic epic = new Epic("Test Epic", "Description");
        epic.addSubtaskId(1);

        List<Integer> subtaskIds = epic.getSubtaskIds();
        subtaskIds.add(2);

        Assertions.assertEquals(1, epic.getSubtaskIds().size());
    }
}