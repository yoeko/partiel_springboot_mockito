package eu.ensup.partiel_spring.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import eu.ensup.partiel_spring.entities.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
		
	public Student findByMailAddress(String mail);
	
	public List<Student> findAllByFirstNameAndLastName(String firstName, String lastName);
	
}
