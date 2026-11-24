package ma.estem.demowsrestsddi.repositories;

import ma.estem.demowsrestsddi.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByFirstNameStudent(String fn);
}