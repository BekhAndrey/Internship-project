package controller;

import com.bekh.internship.config.SpringConfig;
import com.bekh.internship.dto.RequestEmployeeDto;
import com.bekh.internship.service.DepartmentService;
import com.bekh.internship.service.EmployeeService;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ContextConfiguration(classes = {SpringConfig.class})
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
public class EmployeeControllerTest {

  private MockMvc mockMvc;
  private ObjectMapper mapper;

  @Autowired private WebApplicationContext webApplicationContext;

  @Autowired private EmployeeService employeeService;

  @Autowired private DepartmentService departmentService;

  @BeforeEach
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    mapper = new ObjectMapper();
  }

  @Transactional
  @Test
  public void getEmployeesTest() throws Exception {
    mockMvc.perform(get("/employees")).andExpect(status().isOk());
  }

  @Transactional
  @Test
  public void createEmployeeTest() throws Exception {
    RequestEmployeeDto requestEmployeeDto = new RequestEmployeeDto();
    requestEmployeeDto.setFirstName("Test first name");
    requestEmployeeDto.setLastName("Test last name");
    requestEmployeeDto.setEmail("test@gmail.com");
    requestEmployeeDto.setPassword("qwerty");
    requestEmployeeDto.setPosition("Test position");
    requestEmployeeDto.setDepartmentTitle(departmentService.findAll().get(0).getTitle());
    String json = mapper.writeValueAsString(requestEmployeeDto);
    mockMvc
        .perform(
            post("/employees")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
        .andExpect(status().isCreated());
  }

  @Transactional
  @Test
  public void updateEmployeeTest() throws Exception {
    RequestEmployeeDto employeeToUpdate = createEmployeeDto();
    employeeToUpdate.setEmail("newEmail@gmail.com");
    String json = mapper.writeValueAsString(employeeToUpdate);
    mockMvc
        .perform(
            put("/employees/edit/{id}", employeeToUpdate.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.email").value("newEmail@gmail.com"));
  }

  @Transactional
  @Test
  public void deleteEmployeeTest() throws Exception {
    RequestEmployeeDto employeeToDelete = createEmployeeDto();
    String json = mapper.writeValueAsString(employeeToDelete);
    mockMvc
        .perform(
            delete("/employees/delete/{id}", employeeToDelete.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
        .andExpect(status().isOk());
  }

  private RequestEmployeeDto createEmployeeDto() {
    RequestEmployeeDto employeeDto = new RequestEmployeeDto();
    employeeDto.setFirstName("Test first name");
    employeeDto.setLastName("Test last name");
    employeeDto.setEmail("test@gmail.com");
    employeeDto.setPassword("qwerty");
    employeeDto.setPosition("Test position");
    employeeDto.setDepartmentTitle(departmentService.findAll().get(0).getTitle());
    employeeDto.setId(employeeService.save(employeeDto).getId());
    return employeeDto;
  }
}
