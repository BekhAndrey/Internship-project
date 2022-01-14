package it;

import com.bekh.internship.config.JpaConfig;
import com.bekh.internship.config.LiquibaseConfig;
import com.bekh.internship.config.SpringConfig;
import com.bekh.internship.dto.DepartmentDto;
import com.bekh.internship.service.DepartmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ContextConfiguration(classes = {SpringConfig.class})
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
public class DepartmentControllerTest {

  private MockMvc mockMvc;
  private ObjectMapper mapper;

  @Autowired private WebApplicationContext webApplicationContext;

  @Autowired private DepartmentService departmentService;

  @BeforeEach
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    mapper = new ObjectMapper();
  }

  @Transactional
  @Test
  public void getDepartmentsTest() throws Exception {
    mockMvc.perform(get("/departments")).andExpect(status().isOk());
  }

  @Transactional
  @Test
  public void createDepartmentTest() throws Exception {
    DepartmentDto departmentDto = new DepartmentDto();
    departmentDto.setTitle("Test Department");
    String json = mapper.writeValueAsString(departmentDto);
    mockMvc
        .perform(
            post("/departments")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
        .andExpect(status().isCreated());
  }

  @Transactional
  @Test
  public void updateDepartmentTest() throws Exception {
    DepartmentDto departmentToUpdate = createDepartmentDto();
    departmentToUpdate.setTitle("Updated title");
    String json = mapper.writeValueAsString(departmentToUpdate);
    mockMvc
        .perform(
            put("/departments/{id}", departmentToUpdate.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.title").value("Updated title"))
        .andExpect(content().json(json));
  }

  @Transactional
  @Test
  public void deleteDepartmentTest() throws Exception {
    DepartmentDto departmentDto = createDepartmentDto();
    String json = mapper.writeValueAsString(departmentDto);
    mockMvc
        .perform(
            delete("/departments/{id}", departmentDto.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
        .andExpect(status().isOk());
  }

  private DepartmentDto createDepartmentDto() {
    DepartmentDto departmentDto = new DepartmentDto();
    departmentDto.setTitle("Test department");
    return departmentService.save(departmentDto);
  }
}
