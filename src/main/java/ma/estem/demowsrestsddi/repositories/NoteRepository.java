package ma.estem.demowsrestsddi.repositories;

import ma.estem.demowsrestsddi.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByStudentIdStudent(Long idStudent);
}