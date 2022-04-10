package jp.shisato.javaproject.javatasklist;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

@Controller
public class HomeController {
    private String currentDateTime;

    HomeController() {
        currentDateTime = LocalDateTime.now().toString();
    }

    public void setDateTime(String dateTime) { currentDateTime = dateTime; }

    @RequestMapping(value = "/hello")
    @ResponseBody
    String helloWorld() {
        return """
                <html>
                    <head><title>Hello world!!</title></head>
                    <body>
                        <h1>Hello world!!</h1>
                        Current time is %s.
                    </body>
                </html>
                """.formatted(currentDateTime);
    }
}
