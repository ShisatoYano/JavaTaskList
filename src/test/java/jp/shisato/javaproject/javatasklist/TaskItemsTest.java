package jp.shisato.javaproject.javatasklist;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskItemsTest {
    @Test
    void item() {
        TaskItems taskItems = new TaskItems();
        assertNull(taskItems.item(0));
    }

    @Test
    void add() {
        TaskItems taskItems = new TaskItems();
        taskItems.add("Task1", "2022-04-10");
        assertEquals("Task1", taskItems.item(0).task());
        assertEquals("2022-04-10", taskItems.item(0).deadline());
        assertFalse(taskItems.item(0).done());
    }

    @Test
    void items() {
        TaskItems taskItems = new TaskItems();
        taskItems.add("Task1", "2022-04-10");
        assertEquals("Task1", taskItems.items().get(0).task());
        assertEquals("2022-04-10", taskItems.items().get(0).deadline());
    }

    @Test
    void itemNumber() {
        TaskItems taskItems = new TaskItems();
        assertEquals(0, taskItems.itemNumber());
        taskItems.add("Task1", "2022-04-10");
        assertEquals(1, taskItems.itemNumber());
        taskItems.add("Task2", "2022-05-13");
        assertEquals(2, taskItems.itemNumber());
    }

    @Test
    void itemList() {
        TaskItems taskItems = new TaskItems();
        taskItems.add("Task1", "2022-04-10");
        taskItems.add("Task2", "2022-05-13");
        assertTrue(taskItems.itemList().contains("Task1"));
        assertTrue(taskItems.itemList().contains("2022-04-10"));
        assertTrue(taskItems.itemList().contains("Task2"));
        assertTrue(taskItems.itemList().contains("2022-05-13"));
    }
}