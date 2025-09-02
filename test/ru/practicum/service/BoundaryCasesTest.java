package ru.practicum.service;

import main.ru.practicum.service.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoundaryCasesTest {
    private final TaskManager manager = new InMemoryTaskManager();

    @Test
    void shouldHandleNullInputs() {
        assertDoesNotThrow(() -> {
            manager.createTask(null);
            manager.updateTask(null);
            manager.createEpic(null);
            manager.updateEpic(null);
            manager.createSubtask(null);
            manager.updateSubtask(null);
        });
    }

    @Test
    void shouldHandleNonExistentIds() {
        assertNull(manager.getTask(999));
        assertNull(manager.getEpic(999));
        assertNull(manager.getSubtask(999));

        assertDoesNotThrow(() -> {
            manager.deleteTask(999);
            manager.deleteEpic(999);
            manager.deleteSubtask(999);
        });
    }

    @Test
    void shouldHandleEmptyLists() {
        Assertions.assertTrue(manager.getAllTasks().isEmpty());
        Assertions.assertTrue(manager.getAllEpics().isEmpty());
        Assertions.assertTrue(manager.getAllSubtasks().isEmpty());
        Assertions.assertTrue(manager.getHistory().isEmpty());

        assertDoesNotThrow(() -> {
            manager.deleteAllTasks();
            manager.deleteAllEpics();
            manager.deleteAllSubtasks();
        });
    }
}