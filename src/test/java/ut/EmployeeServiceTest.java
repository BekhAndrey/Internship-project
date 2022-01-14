package ut;

import com.bekh.internship.dto.EmployeeRequestDto;
import com.bekh.internship.dto.EmployeeResponseDto;
import com.bekh.internship.model.Department;
import com.bekh.internship.model.Employee;
import com.bekh.internship.repository.DepartmentRepository;
import com.bekh.internship.repository.EmployeeRepository;
import com.bekh.internship.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class EmployeeServiceTest {

  @Mock private DepartmentRepository departmentRepository;

  @Mock private EmployeeRepository employeeRepository;

  @InjectMocks private EmployeeServiceImpl employeeService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void findAllEmployeesTest() {
    List<Employee> employees = new ArrayList<>();
    employees.add(createEmployee(1L, "email1@gmail.com"));
    employees.add(createEmployee(2L, "email2@gmail.com"));
    employees.add(createEmployee(3L, "email3@gmail.com"));
    given(employeeRepository.findAll()).willReturn(employees);
    List<EmployeeResponseDto> expected = employeeService.findAll();
    for (int i = 0; i < employees.size(); i++) {
      assertEquals(expected.get(i).getEmail(), employees.get(i).getEmail());
    }
  }

  @Test
  public void saveEmployeeTest() {
    EmployeeRequestDto requestEmployeeDto = new EmployeeRequestDto();
    requestEmployeeDto.setEmail("email1@gmail.com");
    requestEmployeeDto.setFirstName("Test first name");
    requestEmployeeDto.setLastName("Test last name");
    requestEmployeeDto.setPassword("qwerty");
    requestEmployeeDto.setPosition("test position");
    requestEmployeeDto.setDepartmentTitle("");
    given(departmentRepository.findByTitle(any(String.class)))
        .willReturn(Optional.of(new Department(1L, "Test title", emptyList())));
    given(employeeRepository.save(any(Employee.class))).willAnswer(i -> i.getArguments()[0]);
    EmployeeResponseDto savedEmployeeDto = employeeService.save(requestEmployeeDto);
    assertNotNull(savedEmployeeDto);
    verify(employeeRepository).save(any(Employee.class));
  }

  @Test
  public void updateEmployeeTest() {
    EmployeeRequestDto requestEmployeeDto = new EmployeeRequestDto();
    requestEmployeeDto.setId(1L);
    requestEmployeeDto.setEmail("email1@gmail.com");
    requestEmployeeDto.setFirstName("Test first name");
    requestEmployeeDto.setLastName("Test last name");
    requestEmployeeDto.setPassword("qwerty");
    requestEmployeeDto.setPosition("test position");
    requestEmployeeDto.setDepartmentTitle("");
    given(departmentRepository.findByTitle(any(String.class)))
            .willReturn(Optional.of(new Department(1L, "Test title", emptyList())));
    given(employeeRepository.findById(1L)).willReturn(Optional.of(createEmployee(1L, "email1@gmail.com")));
    given(employeeRepository.save(any(Employee.class))).willAnswer(i -> i.getArguments()[0]);
    EmployeeResponseDto updatedEmployeeDto = employeeService.update(requestEmployeeDto);
    assertEquals(requestEmployeeDto.getEmail(), updatedEmployeeDto.getEmail());
    verify(employeeRepository).save(any(Employee.class));
  }

      @Test
      public void deleteEmployeeByIdTest() {
          Long id = 1L;
          employeeService.deleteById(id);
          verify(employeeRepository).deleteById(id);
      }

      @Test
      public void findEmployeeByEmailTest() {
          Employee employee = createEmployee(1L, "email1@gmail.com");
          given(employeeRepository.findByEmail("email1@gmail.com"))
                  .willReturn(
                          Optional.of(employee));
          EmployeeResponseDto dto = employeeService.findByEmail("email1@gmail.com");
          assertNotNull(dto);
      }

      @Test
      public void findEmployeeByIdTest() {
          Long id = 1L;
          Employee employee = createEmployee(1L, "email1@gmail.com");
          given(employeeRepository.findById(id))
                  .willReturn(
                          Optional.of(employee));
          EmployeeResponseDto dto = employeeService.findById(id);
          assertNotNull(dto);
      }

  public Employee createEmployee(Long id, String email) {
    Employee employee = new Employee();
    employee.setId(id);
    employee.setFirstName("Test first name");
    employee.setLastName("Test last name");
    employee.setEmail(email);
    employee.setPassword("qwerty");
    employee.setJobTitle("test position");
    employee.setDepartment(new Department(1L, "Test title", emptyList()));
    return employee;
  }

}
