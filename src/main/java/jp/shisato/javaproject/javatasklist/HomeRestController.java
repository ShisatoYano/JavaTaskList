package jp.shisato.javaproject.javatasklist;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;

@RestController
public class HomeRestController {
    @RequestMapping(value = "/resthello")
    String helloWorld() {
        return """
                Hello world!!
                Current time is %s.
                """.formatted(LocalDateTime.now());
    }
}
