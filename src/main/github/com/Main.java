package main.github.com;

import main.github.com.model.*;
import main.github.com.service.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = Managers.getDefault();

        Task task1 = new Task("Task 1", "Description 1");
        Task task2 = new Task("Task 2", "Description 2");
        int taskId1 = manager.createTask(task1);
        int taskId2 = manager.createTask(task2);


        Epic epic1 = new Epic("Epic 1", "Epic description 1");
        Epic epic2 = new Epic("Epic 2", "Epic description 2");
        int epicId1 = manager.createEpic(epic1);
        int epicId2 = manager.createEpic(epic2);

        Subtask subtask1 = new Subtask("Subtask 1", "Sub description 1", epicId1);
        Subtask subtask2 = new Subtask("Subtask 2", "Sub description 2", epicId1);
        Subtask subtask3 = new Subtask("Subtask 3", "Sub description 3", epicId2);
        int subtaskId1 = manager.createSubtask(subtask1);
        int subtaskId2 = manager.createSubtask(subtask2);
        int subtaskId3 = manager.createSubtask(subtask3);


        System.out.println("=== История после создания ===");
        printHistory(manager);

        manager.getTask(taskId1);
        manager.getEpic(epicId1);
        manager.getSubtask(subtaskId1);

        System.out.println("=== История после просмотра ===");
        printHistory(manager);

        subtask1.setStatus(Status.DONE);
        manager.updateSubtask(subtask1);
        subtask2.setStatus(Status.IN_PROGRESS);
        manager.updateSubtask(subtask2);

        System.out.println("=== Статус эпика после обновления подзадач ===");
        System.out.println(manager.getEpic(epicId1).getStatus());
    }

    private static void printHistory(TaskManager manager) {
        List<Task> history = manager.getHistory();
        if (history.isEmpty()) {
            System.out.println("История пуста");
        } else {
            history.forEach(System.out::println);
        }
    }
}