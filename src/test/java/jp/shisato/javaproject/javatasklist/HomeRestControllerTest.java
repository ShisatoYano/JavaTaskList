package jp.shisato.javaproject.javatasklist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

class HomeRestControllerTest {
    private MockMvc mockMvc; // Mock of Spring MVC
    private final String TEST_FILE_NAME = "test_date_time.txt";
    private String dateTime;
    private HomeRestController controller;

    HomeRestControllerTest() throws Exception {
        String filePath =
                HomeRestController.class.getClassLoader()
                        .getResource(TEST_FILE_NAME).getPath();

        File testFile = new File(filePath);
        try (BufferedReader br = new BufferedReader(new FileReader(testFile))) {
            String text;
            while ((text = br.readLine()) != null) {
                dateTime = text;
            }
        }
    }

    @BeforeEach
    void settingMock() {
        controller = new HomeRestController();
        controller.setDateTime(dateTime);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void requestByGet() throws Exception {
        mockMvc.perform(get("/resthello"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        """
                        Hello world!!
                        Current time is %s.
                        """.formatted(dateTime)
                ));
    }

    @Test
    void requestByPost() throws Exception {
        mockMvc.perform(post("/resthello"))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    void addItem() throws Exception {
        mockMvc.perform(get("/restadd")
                        .param("task", "Task1")
                        .param("deadline", "2022-04-10"))
                .andExpect(status().isOk());
    }

    @Test
    void listItems() throws Exception {
        mockMvc.perform(get("/restlist"))
                .andExpect(status().isOk());
    }
}