package jp.shisato.javaproject.javatasklist;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class HomeRestController {
    private String currentDateTime;

    record TaskItem(String id, String task, String deadline, boolean done) {}
    private List<TaskItem> taskItems = new ArrayList<>();

    HomeRestController() {
        this.currentDateTime = LocalDateTime.now().toString();
    }

    public void setDateTime(String dateTime) {
        this.currentDateTime = dateTime;
    }

    @RequestMapping(value = "/resthello", method = RequestMethod.GET)
    String helloWorld() {
        return """
                Hello world!!
                Current time is %s.
                """.formatted(this.currentDateTime);
    }

    @GetMapping(value = "/restadd")
    String addItem(@RequestParam("task") String task,
                   @RequestParam("deadline") String deadline) {
        String id = UUID.randomUUID().toString().substring(0, 8);
        TaskItem item = new TaskItem(id, task, deadline, false);
        taskItems.add(item);

        return "Task was added successfully.";
    }

    @GetMapping(value = "/restlist")
    String listItems() {
        String result = taskItems.stream()
                .map(TaskItem::toString)
                .collect(Collectors.joining(", "));

        return result;
    }
}
