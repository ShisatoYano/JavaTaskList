package jp.shisato.javaproject.javatasklist;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class TaskItems {
    record TaskItem(String id, String task, String deadline, boolean done) {}
    private List<TaskItem> taskItems;

    TaskItems() { taskItems = new ArrayList<>(); }

    public void add(String task, String deadline) {
        String id = UUID.randomUUID().toString().substring(0, 8);
        TaskItem item = new TaskItem(id, task, deadline, false);
        taskItems.add(item);
    }

    public TaskItem item(int index) {
        if (taskItems.size() == 0) return null;
        return taskItems.get(index);
    }

    public int itemNumber() { return taskItems.size(); }

    public String itemList() {
        return taskItems.stream().map(TaskItem::toString)
                .collect(Collectors.joining(", "));
    }
}
