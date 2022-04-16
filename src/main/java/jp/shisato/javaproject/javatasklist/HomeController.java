package jp.shisato.javaproject.javatasklist;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
public class HomeController {
    private LocalDateTime currentDateTime;

    HomeController() {
        currentDateTime = LocalDateTime.now();
    }

    public void setDateTime(LocalDateTime dateTime) { currentDateTime = dateTime; }

    @RequestMapping(value = "/hello")
    String helloWorld(Model model) {
        model.addAttribute("time", LocalDateTime.now());
        return "hello";
    }
}
