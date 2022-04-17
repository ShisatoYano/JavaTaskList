package jp.shisato.javaproject.javatasklist;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    private TaskItems taskItems;

    HomeController() { taskItems = new TaskItems(); }

    @GetMapping("/list")
    String listItems(Model model) {
        model.addAttribute("taskList", taskItems.items());
        return "home";
    }

    @GetMapping("/add")
    String addItem(@RequestParam("task") String task,
                   @RequestParam("deadline") String deadline) {
        taskItems.add(task, deadline);
        return "redirect:/list";
    }
}
