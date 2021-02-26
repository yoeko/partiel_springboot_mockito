package eu.ensup.partiel_spring;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import eu.ensup.partiel_spring.entities.Course;
import eu.ensup.partiel_spring.repositories.CourseRepository;
import eu.ensup.partiel_spring.service.CourseServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Optional;

@SpringBootTest
public class CourseServiceTest {
	
	

	@Test
	public void getByIdTest() {
		Course returnCourse = new Course("Histoire", 20);		
		CourseRepository mockedCourseRepo = mock(CourseRepository.class);
		when(mockedCourseRepo.findById("Histoire")).thenReturn(Optional.of(returnCourse));
		
		//Test
		CourseServiceImpl courseService = new CourseServiceImpl(mockedCourseRepo);
		Course actualCourse = courseService.getCourseById("Histoire");
		assertEquals(returnCourse, actualCourse);
		verify(mockedCourseRepo).findById("Histoire");
		
	}
	
}
