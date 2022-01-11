package service;

import com.bekh.internship.dto.ProjectDto;
import com.bekh.internship.model.Project;
import com.bekh.internship.repository.ProjectRepository;
import com.bekh.internship.service.impl.ProjectServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class ProjectServiceTest {

  @Mock private ProjectRepository projectRepository;

  @InjectMocks private ProjectServiceImpl projectService;

  @BeforeEach
  public void setUp(){
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void findAllProjectsTest() {
    List<Project> projects = new ArrayList<>();
    projects.add(new Project(1L, "Title1", LocalDate.now(), LocalDate.now(), emptyList()));
    projects.add(new Project(2L, "Title2", LocalDate.now(), LocalDate.now(), emptyList()));
    projects.add(new Project(3L, "Title3", LocalDate.now(), LocalDate.now(), emptyList()));
    given(projectRepository.findAll()).willReturn(projects);
    List<ProjectDto> expected = projectService.findAll();
    for (int i = 0; i < projects.size(); i++) {
      assertEquals(expected.get(i).getTitle(), projects.get(i).getTitle());
    }
  }

  @Test
  public void saveProjectTest() {
    ProjectDto projectDto = new ProjectDto();
    projectDto.setTitle("Test project");
    projectDto.setStartDate(LocalDate.now());
    projectDto.setEndDate(LocalDate.now());
    given(projectRepository.save(any(Project.class))).willAnswer(i -> i.getArguments()[0]);
    ProjectDto savedProjectDto = projectService.save(projectDto);
    assertNotNull(savedProjectDto);
    verify(projectRepository).save(any(Project.class));
  }

  @Test
  public void updateProjectTest() {
    ProjectDto dto = new ProjectDto(1L, "Title", LocalDate.now(), LocalDate.now());
    given(projectRepository.findById(1L))
        .willReturn(
            Optional.of(new Project(1L, "Title", LocalDate.now(), LocalDate.now(), emptyList())));
    given(projectRepository.save(any(Project.class))).willAnswer(i -> i.getArguments()[0]);
    projectService.update(dto);
    verify(projectRepository).save(any(Project.class));
  }

  @Test
  public void deleteProjectByIdTest() {
    Long id = 1L;
    projectService.deleteById(id);
    verify(projectRepository).deleteById(id);
  }

  @Test
  public void findProjectByTitleTest() {
    Project project = new Project(1L, "Test title", LocalDate.now(), LocalDate.now(), emptyList());
    given(projectRepository.findByTitle("Test title"))
        .willReturn(
            Optional.of(project));
    ProjectDto dto = projectService.findByTitle("Test title");
    assertNotNull(dto);
  }

  @Test
  public void findProjectByIdTest() {
    Long id = 1L;
    Project project = new Project(1L, "Test title", LocalDate.now(), LocalDate.now(), emptyList());
    given(projectRepository.findById(id))
            .willReturn(
                    Optional.of(project));
    ProjectDto dto = projectService.findById(id);
    assertNotNull(dto);
  }
}
