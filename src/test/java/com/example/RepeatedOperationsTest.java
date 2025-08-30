package test.java.com.example;

import main.java.com.service.*;
import main.java.com.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RepeatedOperationsTest {
    private final TaskManager manager = new InMemoryTaskManager();

    @Test
    void shouldHandleRepeatedAddRemove() {
        Task task = new Task("Task", "Desc");
        int id = manager.createTask(task);

        for (int i = 0; i < 10; i++) {
            manager.getTask(id);
        }

        Assertions.assertEquals(1, manager.getHistory().size());

        manager.deleteTask(id);
        manager.deleteTask(id); // Не должно вызывать ошибок

        Assertions.assertTrue(manager.getHistory().isEmpty());
    }

    @Test
    void shouldHandleHistoryWithManyTasks() {
        for (int i = 0; i < 11; i++) {
            Task task = new Task("Task " + i, "Desc");
            int id = manager.createTask(task);
            manager.getTask(id);
        }

        List<Task> history = manager.getHistory();
        assertEquals(10, history.size());
        Assertions.assertEquals("Task 1", history.get(0).getName()); // Первая задача должна быть вытеснена
        Assertions.assertEquals("Task 10", history.get(9).getName());
    }
}