package it;

import com.bekh.internship.config.SpringConfig;
import com.bekh.internship.dto.ProjectDto;
import com.bekh.internship.service.ProjectService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ContextConfiguration(classes = {SpringConfig.class})
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
public class ProjectControllerTest {

  private MockMvc mockMvc;
  private ObjectMapper mapper;

  @Autowired private WebApplicationContext webApplicationContext;

  @Autowired private ProjectService projectService;

  @BeforeEach
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
  }

  @Transactional
  @Test
  public void getProjectsTest() throws Exception {
    mockMvc.perform(get("/projects")).andExpect(status().isOk());
  }

  @Transactional
  @Test
  public void createProjectTest() throws Exception {
    ProjectDto projectDto = new ProjectDto();
    projectDto.setTitle("Test title");
    projectDto.setStartDate(LocalDate.now());
    projectDto.setEndDate(LocalDate.now());
    String json = mapper.writeValueAsString(projectDto);
    mockMvc
        .perform(
            post("/projects")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
        .andExpect(status().isCreated());
  }

  @Transactional
  @Test
  public void updateProjectTest() throws Exception {
    ProjectDto projectDto = createProjectDto();
    projectDto.setTitle("New title");
    String json = mapper.writeValueAsString(projectDto);
    mockMvc
        .perform(
            put("/projects/{id}", projectDto.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.title").value("New title"))
        .andExpect(content().json(json));
    ;
  }

  @Transactional
  @Test
  public void deleteProjectTest() throws Exception {
    ProjectDto projectDto = createProjectDto();
    String json = mapper.writeValueAsString(projectDto);
    mockMvc
        .perform(
            delete("/projects/{id}", projectDto.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
        .andExpect(status().isOk());
  }

  private ProjectDto createProjectDto() {
    ProjectDto projectDto = new ProjectDto();
    projectDto.setTitle("Test title");
    projectDto.setStartDate(LocalDate.now());
    projectDto.setEndDate(LocalDate.now());
    return projectService.save(projectDto);
  }
}
