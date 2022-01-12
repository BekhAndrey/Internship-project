package controller;

import com.bekh.internship.config.SpringConfig;
import com.bekh.internship.dto.PositionDto;
import com.bekh.internship.dto.ProjectDto;
import com.bekh.internship.service.EmployeeService;
import com.bekh.internship.service.PositionService;
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
public class PositionControllerTest {

  private MockMvc mockMvc;
  private ObjectMapper mapper;

  @Autowired private WebApplicationContext webApplicationContext;

  @Autowired private PositionService positionService;
  @Autowired private EmployeeService employeeService;
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
  public void getPositionsTest() throws Exception {
    mockMvc.perform(get("/positions")).andExpect(status().isOk());
  }

  @Transactional
  @Test
  public void createPositionTest() throws Exception {
    PositionDto positionDto = new PositionDto();
    positionDto.setProjectTitle(projectService.findAll().get(0).getTitle());
    positionDto.setEmployeeEmail(employeeService.findAll().get(0).getEmail());
    positionDto.setPositionStartDate(LocalDate.now());
    positionDto.setPositionEndDate(LocalDate.now());
    String json = mapper.writeValueAsString(positionDto);
    mockMvc
        .perform(
            post("/positions")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
        .andExpect(status().isCreated());
  }

  @Transactional
  @Test
  public void updatePositionTest() throws Exception {
    PositionDto positionDto = createPositionDto();
    positionDto.setPositionEndDate(positionDto.getPositionEndDate().plusDays(5));
    String json = mapper.writeValueAsString(positionDto);
    mockMvc
        .perform(
            put("/positions/edit/{id}", positionDto.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
        .andExpect(status().isOk())
        .andExpect(content().json(json));
    ;
  }

  @Transactional
  @Test
  public void deletePositionTest() throws Exception {
    PositionDto positionDto = createPositionDto();
    String json = mapper.writeValueAsString(positionDto);
    mockMvc
        .perform(
            delete("/positions/delete/{id}", positionDto.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
        .andExpect(status().isOk());
  }

  private PositionDto createPositionDto() {
    PositionDto positionDto = new PositionDto();
    positionDto.setProjectTitle(projectService.findAll().get(0).getTitle());
    positionDto.setEmployeeEmail(employeeService.findAll().get(0).getEmail());
    positionDto.setPositionStartDate(LocalDate.now());
    positionDto.setPositionEndDate(LocalDate.now());
    return positionService.save(positionDto);
  }
}
