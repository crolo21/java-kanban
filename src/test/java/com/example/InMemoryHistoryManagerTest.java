package test.java.com.example;

import main.java.com.service.*;
import main.java.com.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryHistoryManagerTest {
    private HistoryManager historyManager;
    private Task task1, task2;

    @BeforeEach
    void setUp() {
        historyManager = new InMemoryHistoryManager();
        task1 = new Task("Task 1", "Desc");
        task1.setId(1);
        task2 = new Task("Task 2", "Desc");
        task2.setId(2);
    }

    @Test
    void shouldAddTasksToHistory() {
        historyManager.add(task1);
        historyManager.add(task2);

        List<Task> history = historyManager.getHistory();
        assertEquals(2, history.size());
        assertEquals(task1, history.get(0));
        assertEquals(task2, history.get(1));
    }

    @Test
    void shouldNotAddNullTask() {
        historyManager.add(null);
        Assertions.assertTrue(historyManager.getHistory().isEmpty());
    }

    @Test
    void shouldRemoveTasksFromHistory() {
        historyManager.add(task1);
        historyManager.add(task2);

        historyManager.remove(task1.getId());
        List<Task> history = historyManager.getHistory();
        assertEquals(1, history.size());
        assertEquals(task2, history.get(0));
    }

    @Test
    void shouldNotDuplicateTasksInHistory() {
        historyManager.add(task1);
        historyManager.add(task1);
        historyManager.add(task1);

        List<Task> history = historyManager.getHistory();
        assertEquals(1, history.size());
    }

    @Test
    void shouldMaintainOrderAfterMultipleOperations() {
        historyManager.add(task1);
        historyManager.add(task2);
        historyManager.add(task1); // Должен переместиться в конец

        List<Task> history = historyManager.getHistory();
        assertEquals(2, history.size());
        assertEquals(task2, history.get(0));
        assertEquals(task1, history.get(1));
    }
}