package com.github.darogina.ideas.controller.api.v1;

import com.github.darogina.ideas.AbstractWebTests;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class HealthCheckAPIv1ControllerIT extends AbstractWebTests {

    @Test
    public void simple() throws Exception {
        ResultActions result = mockMvc.perform(get("/api/v1/healthCheck").accept(MediaType.APPLICATION_JSON).characterEncoding("UTF-8"));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF_8))
                .andExpect(jsonPath("$.status").value(true));
    }
}
