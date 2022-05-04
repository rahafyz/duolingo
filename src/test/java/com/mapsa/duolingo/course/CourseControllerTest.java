package com.mapsa.duolingo.course;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CourseControllerTest {


    CourseController controller;

    @InjectMocks
    CourseService service;

    @Mock
    CourseRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void init() {
        CourseController controller = new CourseController(service);

        this.mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .build();

        objectMapper = new ObjectMapper();
    }

    @Test
    void save() throws Exception {
        Long beforeSave = repository.count();
        CourseDto dto = new CourseDto();
        mockMvc.perform(post("/course/course").contentType("application/json")
                .content(objectMapper.writeValueAsBytes(dto)))
                .andExpect(status().isOk());
        Long afterSave = repository.count();

        repository.findAll().get((int) (afterSave - 1));
    }
}
