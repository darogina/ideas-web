package com.github.darogina.ideas.controller.api.v1;

import com.github.darogina.ideas.AbstractCrudServiceTest;
import com.github.darogina.ideas.model.api.v1.Author;
import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AuthorsAPIv1ControllerIT extends AbstractCrudServiceTest {

    private static final String URL = "/api/v1/authors";

    @Test
    public void createTest() throws Exception {
        Author author = new Author();
        author.setName("Author Name");

        ResultActions result = create(URL, author);

        result.andExpect(status().isCreated())
                .andExpect(content().contentType(APPLICATION_JSON_UTF_8))
                .andExpect(jsonPath("$.name").value(author.getName()));
    }
}
