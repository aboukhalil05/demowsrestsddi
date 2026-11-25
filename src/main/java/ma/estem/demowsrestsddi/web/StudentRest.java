package ma.estem.demowsrestsddi.web; // Package des contrôleurs REST

import lombok.AllArgsConstructor; // Génère un constructeur permettant l'injection automatique des repositories
import ma.estem.demowsrestsddi.entities.Note; // Entité Note
import ma.estem.demowsrestsddi.entities.Student; // Entité Student
import ma.estem.demowsrestsddi.repositories.NoteRepository; // Repository des notes
import ma.estem.demowsrestsddi.repositories.StudentRepository; // Repository des étudiants
import org.springframework.web.bind.annotation.*; // Annotations REST (@RestController, @GetMapping, @PostMapping, etc.)

import java.util.LinkedHashMap; // Map qui conserve l'ordre d'insertion des clés pour un JSON propre
import java.util.List; // Type List
import java.util.Map; // Type Map (clé -> valeur, converti en objet JSON)

@RestController // Indique que cette classe est un Web Service REST (les retours sont convertis en JSON)
@RequestMapping("/students") // Préfixe d'URL commun à toutes les méthodes de cette classe
@AllArgsConstructor // Injecte automatiquement studentRepository et noteRepository via le constructeur
public class StudentRest {

    private StudentRepository studentRepository; // Accès à la table STUDENT
    private NoteRepository noteRepository; // Accès à la table NOTE (pour calculer les moyennes)

    @GetMapping // Répond à GET http://localhost:8095/students
    public List<Student> getAllStudents() {
        return studentRepository.findAll(); // Retourne la liste complète des étudiants en JSON
    }

    @GetMapping("/{id}") // Répond à GET http://localhost:8095/students/1
    public Student getStudentById(@PathVariable Long id) { // @PathVariable extrait {id} de l'URL
        return studentRepository.findById(id).orElse(null); // Retourne l'étudiant ou null s'il n'existe pas
    }

    @GetMapping("/search") // Répond à GET http://localhost:8095/students/search?fn=Ikram
    public List<Student> searchByFirstName(@RequestParam String fn) { // @RequestParam récupère le paramètre ?fn=...
        return studentRepository.findByFirstNameStudent(fn); // Retourne les étudiants ayant ce prénom
    }

    @GetMapping("/filiere/{idFiliere}") // Répond à GET http://localhost:8095/students/filiere/1
    public List<Student> getStudentsByFiliere(@PathVariable Long idFiliere) { // Extrait l'ID de la filière
        return studentRepository.findByFiliereIdFiliere(idFiliere); // Retourne les étudiants de cette filière
    }

    @PostMapping // Répond à POST http://localhost:8095/students
    public Student addStudent(@RequestBody Student student) { // @RequestBody convertit le JSON reçu en objet Student
        return studentRepository.save(student); // Enregistre le nouvel étudiant en base et le renvoie avec son ID
    }

    @PutMapping("/{id}") // Répond à PUT http://localhost:8095/students/1
    public Student updateStudent(@PathVariable Long id, @RequestBody Student studentDetails) {
        Student st = studentRepository.findById(id).orElse(null); // Cherche l'étudiant existant en base
        if (st != null) { // Si l'étudiant existe
            st.setFirstNameStudent(studentDetails.getFirstNameStudent()); // Met à jour le prénom
            st.setLastNameStudent(studentDetails.getLastNameStudent()); // Met à jour le nom
            st.setEmailStudent(studentDetails.getEmailStudent()); // Met à jour l'email
            st.setFiliere(studentDetails.getFiliere()); // Met à jour la filière
            return studentRepository.save(st); // Sauvegarde les modifications (UPDATE SQL)
        }
        return null; // Retourne null si l'ID n'existe pas
    }

    @DeleteMapping("/{id}") // Répond à DELETE http://localhost:8095/students/1
    public void deleteStudent(@PathVariable Long id) { // Extrait l'ID à supprimer
        studentRepository.deleteById(id); // Supprime l'étudiant en base (DELETE SQL)
    }

    // ==================== FONCTIONNALITÉS MÉTIER ====================

    @GetMapping("/{id}/moyenne") // Répond à GET http://localhost:8095/students/1/moyenne
    public Map<String, Object> getMoyenneStudent(@PathVariable Long id) {
        Student student = studentRepository.findById(id).orElse(null); // Récupère l'étudiant
        Map<String, Object> result = new LinkedHashMap<>(); // Prépare la réponse JSON personnalisée
        if (student == null) { // Vérifie si l'étudiant existe
            result.put("erreur", "Étudiant introuvable"); // Ajoute un message d'erreur
            return result; // Renvoie le JSON d'erreur
        }

        double moyenne = calculerMoyenne(id); // Appelle la méthode privée de calcul
        result.put("idStudent", student.getIdStudent()); // Ajoute l'ID au JSON
        result.put("nomComplet", student.getFirstNameStudent() + " " + student.getLastNameStudent()); // Ajoute le nom complet
        result.put("moyenne", moyenne); // Ajoute la moyenne calculée
        result.put("statut", moyenne >= 10 ? "Admis" : "Ajourné"); // Détermine si l'étudiant est admis (>= 10)
        return result; // Renvoie l'objet JSON final
    }

    @GetMapping("/stats") // Répond à GET http://localhost:8095/students/stats
    public Map<String, Object> getStats() {
        List<Student> students = studentRepository.findAll(); // Récupère tous les étudiants
        int totalEtudiants = students.size(); // Compte le nombre total d'étudiants
        int nbAdmis = 0; // Compteur d'étudiants admis
        double sommeMoyennes = 0; // Cumul des moyennes pour la moyenne générale

        for (Student s : students) { // Parcourt chaque étudiant
            double moy = calculerMoyenne(s.getIdStudent()); // Calcule sa moyenne
            sommeMoyennes += moy; // L'ajoute au cumul
            if (moy >= 10) { // Si sa moyenne est >= 10
                nbAdmis++; // Incrémente le nombre d'admis
            }
        }

        // Calcule la moyenne générale arrondie à 2 décimales (évite la division par zéro)
        double moyenneGenerale = totalEtudiants > 0 ? Math.round((sommeMoyennes / totalEtudiants) * 100.0) / 100.0 : 0.0;
        // Calcule le taux de réussite en pourcentage arrondi à 2 décimales
        double tauxReussite = totalEtudiants > 0 ? Math.round(((double) nbAdmis / totalEtudiants) * 10000.0) / 100.0 : 0.0;

        Map<String, Object> stats = new LinkedHashMap<>(); // Prépare la réponse JSON
        stats.put("totalEtudiants", totalEtudiants); // Nombre total
        stats.put("nombreAdmis", nbAdmis); // Nombre d'admis
        stats.put("tauxReussitePourcent", tauxReussite); // Taux de réussite (%)
        stats.put("moyenneGenerale", moyenneGenerale); // Moyenne générale de l'école
        return stats; // Renvoie les statistiques en JSON
    }

    // Méthode interne (non exposée en REST) qui calcule la moyenne pondérée d'un étudiant
    private double calculerMoyenne(Long idStudent) {
        List<Note> notes = noteRepository.findByStudentIdStudent(idStudent); // Récupère toutes les notes de l'étudiant
        if (notes.isEmpty()) return 0.0; // Si aucune note, la moyenne est 0

        double sommePonderee = 0; // Somme de (note * coefficient)
        double sommeCoefs = 0; // Somme des coefficients
        for (Note n : notes) { // Parcourt chaque note
            double coef = n.getModule().getCoefficient(); // Récupère le coefficient du module associé
            sommePonderee += n.getValeur() * coef; // Ajoute note * coef
            sommeCoefs += coef; // Ajoute coef
        }
        // Retourne la moyenne pondérée arrondie à 2 chiffres après la virgule
        return sommeCoefs > 0 ? Math.round((sommePonderee / sommeCoefs) * 100.0) / 100.0 : 0.0;
    }
}