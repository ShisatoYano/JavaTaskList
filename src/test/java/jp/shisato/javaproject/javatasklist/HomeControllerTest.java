package jp.shisato.javaproject.javatasklist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class HomeControllerTest {
    private MockMvc mockMvc; // Mock of Spring MVC
    private HomeController controller;

    HomeControllerTest() {
        controller = new HomeController();
    }

    @BeforeEach
    void settingMock() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void listItems() throws Exception {
        mockMvc.perform(get("/list"))
                .andExpect(status().isOk());
    }

    @Test
    void addItem() throws Exception {
        mockMvc.perform(get("/add")
                        .param("task", "Task1")
                        .param("deadline", "2022-04-10"))
                .andExpect(status().isFound());
    }
}