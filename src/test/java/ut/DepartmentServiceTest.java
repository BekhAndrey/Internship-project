package ut;

import com.bekh.internship.dto.DepartmentDto;
import com.bekh.internship.model.Department;
import com.bekh.internship.repository.DepartmentRepository;
import com.bekh.internship.service.impl.DepartmentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


public class DepartmentServiceTest {

  @Mock
  private DepartmentRepository departmentRepository;

  @InjectMocks
  private DepartmentServiceImpl departmentService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void findAllDepartmentsTest() {

    //Given
    List<Department> departments = new ArrayList<>();
    departments.add(new Department(1L, "Title1", emptyList()));
    departments.add(new Department(1L, "Title2", emptyList()));
    departments.add(new Department(1L, "Title3", emptyList()));
    given(departmentRepository.findAll()).willReturn(departments);

    //When
    List<DepartmentDto> expected = departmentService.findAll();

    //Then
    for (int i = 0; i < departments.size(); i++) {
      assertEquals(expected.get(i).getTitle(), departments.get(i).getTitle());
    }
  }

  @Test
  public void saveDepartmentTest() {
    DepartmentDto departmentDto = new DepartmentDto();
    departmentDto.setTitle("Test project");
    given(departmentRepository.save(any(Department.class))).willAnswer(i -> i.getArguments()[0]);
    DepartmentDto savedDepartmentDto = departmentService.save(departmentDto);
    assertNotNull(savedDepartmentDto);
    verify(departmentRepository).save(any(Department.class));
  }

  @Test
  public void updateDepartmentTest() {
    DepartmentDto dto = new DepartmentDto(1L, "Title");
    given(departmentRepository.findById(1L))
            .willReturn(
                    Optional.of(new Department(1L, "Title",  emptyList())));
    given(departmentRepository.save(any(Department.class))).willAnswer(i -> i.getArguments()[0]);
    DepartmentDto updated = departmentService.update(dto);
    assertEquals(dto.getTitle(), updated.getTitle());
    verify(departmentRepository).save(any(Department.class));
  }

  @Test
  public void deleteDepartmentByIdTest() {
    Long id = 1L;
    departmentService.deleteById(id);
    verify(departmentRepository).deleteById(id);
  }

  @Test
  public void findDepartmentByTitleTest() {
    Department department = new Department(1L, "Test title", emptyList());
    given(departmentRepository.findByTitle("Test title"))
            .willReturn(
                    Optional.of(department));
    DepartmentDto dto = departmentService.findByTitle("Test title");
    assertNotNull(dto);
  }

  @Test
  public void findDepartmentByIdTest() {
    Long id = 1L;
    Department department = new Department(1L, "Test title", emptyList());
    given(departmentRepository.findById(id))
            .willReturn(
                    Optional.of(department));
    DepartmentDto dto = departmentService.findById(id);
    assertNotNull(dto);
  }
}
