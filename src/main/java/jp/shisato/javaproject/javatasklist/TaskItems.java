package jp.shisato.javaproject.javatasklist;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class TaskItems {
    record TaskItem(String id, String task, String deadline, boolean done) {}
    private List<TaskItem> items;

    TaskItems() { items = new ArrayList<>(); }

    public void add(String task, String deadline) {
        String id = UUID.randomUUID().toString().substring(0, 8);
        TaskItem item = new TaskItem(id, task, deadline, false);
        items.add(item);
    }

    public TaskItem item(int index) {
        if (items.size() == 0) return null;
        return items.get(index);
    }

    public List<TaskItem> items() {
        return items;
    }

    public int itemNumber() { return items.size(); }

    public String itemList() {
        return items.stream().map(TaskItem::toString)
                .collect(Collectors.joining(", "));
    }
}
