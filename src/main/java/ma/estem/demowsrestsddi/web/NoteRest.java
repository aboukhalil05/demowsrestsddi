package ma.estem.demowsrestsddi.web; // Package des contrôleurs REST

import lombok.AllArgsConstructor; // Injection par constructeur
import ma.estem.demowsrestsddi.entities.Note; // Entité Note
import ma.estem.demowsrestsddi.repositories.NoteRepository; // Repository Note
import org.springframework.web.bind.annotation.*; // Annotations REST

import java.util.List; // Type List

@RestController // Contrôleur REST
@RequestMapping("/notes") // URL de base : http://localhost:8095/notes
@AllArgsConstructor // Injecte noteRepository automatiquement
public class NoteRest {

    private NoteRepository noteRepository; // Accès à la table NOTE

    @GetMapping // GET /notes : affiche toutes les notes
    public List<Note> getAll() {
        return noteRepository.findAll(); // Retourne toutes les notes
    }

    @GetMapping("/student/{idStudent}") // GET /notes/student/1 : notes d'un étudiant
    public List<Note> getByStudent(@PathVariable Long idStudent) {
        return noteRepository.findByStudentIdStudent(idStudent); // Filtre par ID d'étudiant
    }

    @PostMapping // POST /notes : ajoute une note
    public Note add(@RequestBody Note note) {
        return noteRepository.save(note); // Enregistre en base
    }

    @DeleteMapping("/{id}") // DELETE /notes/1 : supprime une note
    public void delete(@PathVariable Long id) {
        noteRepository.deleteById(id); // Supprime par ID
    }
}