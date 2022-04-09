package jp.shisato.javaproject.javatasklist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

class HomeRestControllerTest {
    private MockMvc mockMvc; // Mock of Spring MVC

    @BeforeEach
    void settingMock() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new HomeRestController()).build();
    }

    @Test
    void requestByGet() throws Exception {
        mockMvc.perform(get("/resthello"))
                .andExpect(status().isOk());
    }

    @Test
    void requestByPost() throws Exception {
        mockMvc.perform(post("/resthello"))
                .andExpect(status().isMethodNotAllowed());
    }
}