package main.ru.practicum.model;

public class Subtask extends Task {
    private final int epicId;

    public Subtask(String name, String description, int epicId) {
        super(name, description);
        if (name == null || description == null) {
            throw new IllegalArgumentException("Name or description cannot be null");
        }
        if (epicId < 0) {
            throw new IllegalArgumentException("Epic ID cannot be negative");
        }
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }
}
