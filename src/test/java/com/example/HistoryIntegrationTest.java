package test.java.com.example;

import main.java.com.service.*;
import main.java.com.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HistoryIntegrationTest {
    private TaskManager manager;

    @BeforeEach
    void setUp() {
        manager = new InMemoryTaskManager();
    }

    @Test
    void shouldTrackTaskHistory() {
        Task task1 = new Task("Task 1", "Desc");
        Task task2 = new Task("Task 2", "Desc");

        int id1 = manager.createTask(task1);
        int id2 = manager.createTask(task2);

        manager.getTask(id1);
        manager.getTask(id2);
        manager.getTask(id1); // Должен переместиться в конец

        List<Task> history = manager.getHistory();
        assertEquals(2, history.size());
        Assertions.assertEquals(id2, history.get(0).getId());
        Assertions.assertEquals(id1, history.get(1).getId());
    }

    @Test
    void shouldRemoveTasksFromHistoryWhenDeleted() {
        Task task = new Task("Task", "Desc");
        int id = manager.createTask(task);

        manager.getTask(id);
        manager.deleteTask(id);

        Assertions.assertTrue(manager.getHistory().isEmpty());
    }

    @Test
    void shouldHandleHistoryWithEpicsAndSubtasks() {
        Epic epic = new Epic("Epic", "Desc");
        int epicId = manager.createEpic(epic);

        Subtask subtask = new Subtask("Subtask", "Desc", epicId);
        int subtaskId = manager.createSubtask(subtask);

        manager.getEpic(epicId);
        manager.getSubtask(subtaskId);

        List<Task> history = manager.getHistory();
        assertEquals(2, history.size());
        assertTrue(history.contains(epic));
        assertTrue(history.contains(subtask));
    }
}