package jp.shisato.javaproject.javatasklist;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@RestController
public class HomeRestController {
    private String currentDateTime;
    private TaskItems taskItems;

    HomeRestController() {
        currentDateTime = LocalDateTime.now().toString();
        taskItems = new TaskItems();
    }

    public void setDateTime(String dateTime) {
        currentDateTime = dateTime;
    }

    @RequestMapping(value = "/resthello")
    String helloWorld() {
        return """
                Hello world!!
                Current time is %s.
                """.formatted(currentDateTime);
    }

    @GetMapping(value = "/restadd")
    String addItem(@RequestParam("task") String task,
                   @RequestParam("deadline") String deadline) {
        taskItems.add(task, deadline);
        return "Task was added successfully.";
    }

    @GetMapping(value = "/restlist")
    String listItems() {
        return taskItems.itemList();
    }
}
