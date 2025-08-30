package test.java.com.example;

import main.java.com.service.*;
import main.java.com.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {
    private TaskManager manager;

    @BeforeEach
    void setUp() {
        manager = new InMemoryTaskManager();
    }

    @Test
    void shouldCreateAndGetTask() {
        Task task = new Task("Test", "Desc");
        int taskId = manager.createTask(task);

        Task savedTask = manager.getTask(taskId);
        assertNotNull(savedTask);
        Assertions.assertEquals(task.getName(), savedTask.getName());
    }

    @Test
    void shouldNotCreateNullTask() {
        Assertions.assertEquals(-1, manager.createTask(null));
    }

    @Test
    void shouldUpdateEpicStatusWhenSubtaskChanges() {
        Epic epic = new Epic("Epic", "Desc");
        int epicId = manager.createEpic(epic);

        Subtask subtask = new Subtask("Subtask", "Desc", epicId);
        int subtaskId = manager.createSubtask(subtask);

        Assertions.assertEquals(Status.NEW, manager.getEpic(epicId).getStatus());

        subtask.setStatus(Status.DONE);
        manager.updateSubtask(subtask);
        Assertions.assertEquals(Status.DONE, manager.getEpic(epicId).getStatus());
    }

    @Test
    void shouldNotAllowSubtaskWithoutEpic() {
        Subtask subtask = new Subtask("Subtask", "Desc", 999); // Несуществующий эпик
        Assertions.assertEquals(-1, manager.createSubtask(subtask));
    }
}