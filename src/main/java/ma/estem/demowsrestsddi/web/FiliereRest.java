package ma.estem.demowsrestsddi.web;

import lombok.AllArgsConstructor;
import ma.estem.demowsrestsddi.entities.Filiere;
import ma.estem.demowsrestsddi.entities.Note;
import ma.estem.demowsrestsddi.entities.Student;
import ma.estem.demowsrestsddi.repositories.FiliereRepository;
import ma.estem.demowsrestsddi.repositories.NoteRepository;
import ma.estem.demowsrestsddi.repositories.StudentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/filieres")
@AllArgsConstructor
public class FiliereRest {

    private FiliereRepository filiereRepository;
    private StudentRepository studentRepository;
    private NoteRepository noteRepository;

    @GetMapping
    public List<Filiere> getAllFilieres() {
        return filiereRepository.findAll();
    }

    @GetMapping("/{id}")
    public Filiere getFiliereById(@PathVariable Long id) {
        return filiereRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Filiere addFiliere(@RequestBody Filiere filiere) {
        return filiereRepository.save(filiere);
    }

    @PutMapping("/{id}")
    public Filiere updateFiliere(@PathVariable Long id, @RequestBody Filiere filiereDetails) {
        Filiere f = filiereRepository.findById(id).orElse(null);
        if (f != null) {
            f.setCodeFiliere(filiereDetails.getCodeFiliere());
            f.setNomFiliere(filiereDetails.getNomFiliere());
            return filiereRepository.save(f);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteFiliere(@PathVariable Long id) {
        filiereRepository.deleteById(id);
    }

    @GetMapping("/{id}/classement")
    public List<Map<String, Object>> getClassementFiliere(@PathVariable Long id) {
        List<Student> students = studentRepository.findByFiliereIdFiliere(id);
        List<Map<String, Object>> classement = new ArrayList<>();

        for (Student s : students) {
            List<Note> notes = noteRepository.findByStudentIdStudent(s.getIdStudent());
            double sommePonderee = 0;
            double sommeCoefs = 0;
            for (Note n : notes) {
                double coef = n.getModule().getCoefficient();
                sommePonderee += n.getValeur() * coef;
                sommeCoefs += coef;
            }
            double moyenne = sommeCoefs > 0 ? Math.round((sommePonderee / sommeCoefs) * 100.0) / 100.0 : 0.0;

            Map<String, Object> ligne = new LinkedHashMap<>();
            ligne.put("idStudent", s.getIdStudent());
            ligne.put("nomComplet", s.getFirstNameStudent() + " " + s.getLastNameStudent());
            ligne.put("moyenne", moyenne);
            classement.add(ligne);
        }

        classement.sort((a, b) -> Double.compare((double) b.get("moyenne"), (double) a.get("moyenne")));

        for (int i = 0; i < classement.size(); i++) {
            classement.get(i).put("rang", i + 1);
        }

        return classement;
    }
}