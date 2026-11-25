package ma.estem.demowsrestsddi.repositories; // Package des repositories

import ma.estem.demowsrestsddi.entities.Student; // Importe l'entité Student
import org.springframework.data.jpa.repository.JpaRepository; // Interface CRUD de Spring Data JPA

import java.util.List; // Importe le type List

// Interface DAO pour gérer la table STUDENT dont la clé primaire est de type Long
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByFirstNameStudent(String fn); // Cherche les étudiants par prénom (WHERE fn_student = ?)
    List<Student> findByFiliereIdFiliere(Long idFiliere); // Cherche tous les étudiants d'une filière via son ID (WHERE id_filiere = ?)
}