package eu.ensup.partiel_spring.controller;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import eu.ensup.partiel_spring.entities.Course;
import eu.ensup.partiel_spring.entities.Student;
import eu.ensup.partiel_spring.exception.StudentNotFoundException;
import eu.ensup.partiel_spring.repositories.StudentRepository;
import eu.ensup.partiel_spring.service.ICourseService;
import eu.ensup.partiel_spring.service.IStudentService;

@RestController
@CrossOrigin
@RequestMapping("/student")
public class StudentWebService {
	
	@Autowired
	private IStudentService studentService;
	
	@Autowired
	private ICourseService courseService;
	
	
	
	public StudentWebService(IStudentService studentService) {
		this.studentService = studentService;
	}
	

	@GetMapping("/getAll")
	public List<Student> getAllStudent(){
		return studentService.getAllStudents();
	}
	
	@RequestMapping(path = "/detail/{id}", method = RequestMethod.GET)
	public ResponseEntity getStudentById(@PathVariable(name = "id") Long id) {
//		Student student;
//		try {
//			student = studentService.findById(id);
//		} catch (Exception e) {
//			throw new StudentNotFoundException("Student not found");
//		}
//		return student;
		
		Student student = studentService.findById(id);
		return new ResponseEntity<>(student, HttpStatus.OK);
		
	}
	
	@RequestMapping(path = "/getByMail/{mail}", method = RequestMethod.GET)
	public Student getStudentByMail(@PathVariable(name = "mail") String mail)
	{
		Student student = studentService.getByMailAddress(mail);
		return student;
	}
	
	@RequestMapping(path = "/search/{firstName}/{lastName}", method = RequestMethod.GET)
	public List<Student> getStudentByFirstNameAndLastName(@PathVariable String firstName, @PathVariable String lastName)
	{
		return studentService.getStudentByFirstNameAndLastName(firstName, lastName);
	}
	
	@PostMapping("/create")
	public void createStudent(@Validated @RequestBody Student student) {
		studentService.createStudent(student);
	}
	
	
	@PutMapping("/update/{id}")
	public void updateStudent(@PathVariable (value = "id") Long id, @RequestBody Student student) {
		
		Student studentFound = studentService.findById(id);
		
		studentFound.setAddress( student.getAddress() );
		studentFound.setBirthDate( student.getBirthDate() );
		studentFound.setFirstName( student.getFirstName() );
		studentFound.setLastName( student.getLastName() );
		studentFound.setMailAddress( student.getMailAddress() );
		studentFound.setNumberPhone( student.getNumberPhone() );
		
		studentService.updateStudent(studentFound);
	}
	
	@PutMapping("/addCourse/{id}")
	public void addCourse(@PathVariable (value = "id") Long id, @RequestBody Course course) {
		Student studentFound = studentService.findById(id);
		
		Set<Course> studentFoundCourses = studentFound.getCourses();
		studentFoundCourses.add( course );
		studentFound.setCourses( studentFoundCourses );
		
		studentService.updateStudent(studentFound);		
		
	}
	
	
	@DeleteMapping("/delete/{id}")
	public void deleteStudent(@PathVariable (value = "id") Long id) {
		
		Student studentFound = studentService.findById(id);
		studentService.deleteStudent(studentFound);
		
	}
	
	

}
