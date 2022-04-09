package jp.shisato.javaproject.javatasklist;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;

@RestController
public class HomeRestController {
    @RequestMapping(value = "/resthello", method = RequestMethod.GET)
    String helloWorld() {
        return "Hello world!!";
    }
}
