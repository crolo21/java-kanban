package main.ru.practicum.service;

import java.util.List;

import main.ru.practicum.model.*;

public interface TaskManager {
    List<Task> getAllTasks();

    int createTask(Task task);

    void updateTask(Task task);

    void deleteTask(int id);

    void deleteAllTasks();

    Task getTask(int id);

    List<Epic> getAllEpics();

    int createEpic(Epic epic);

    void updateEpic(Epic epic);

    void deleteEpic(int id);

    void deleteAllEpics();

    Epic getEpic(int id);

    List<Subtask> getAllSubtasks();

    int createSubtask(Subtask subtask);

    void updateSubtask(Subtask subtask);

    void deleteSubtask(int id);

    void deleteAllSubtasks();

    Subtask getSubtask(int id);

    List<Subtask> getSubtasksByEpic(int epicId);

    List<Task> getHistory();
}