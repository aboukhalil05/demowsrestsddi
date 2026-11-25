package ma.estem.demowsrestsddi.web; // Package des contrôleurs REST

import lombok.AllArgsConstructor; // Injection par constructeur
import ma.estem.demowsrestsddi.entities.Professeur; // Entité Professeur
import ma.estem.demowsrestsddi.repositories.ProfesseurRepository; // Repository Professeur
import org.springframework.web.bind.annotation.*; // Annotations REST

import java.util.List; // Type List

@RestController // Contrôleur REST
@RequestMapping("/professeurs") // URL de base : http://localhost:8095/professeurs
@AllArgsConstructor // Injecte professeurRepository automatiquement
public class ProfesseurRest {

    private ProfesseurRepository professeurRepository; // Accès à la table PROFESSEUR

    @GetMapping // GET /professeurs : affiche tous les professeurs
    public List<Professeur> getAll() {
        return professeurRepository.findAll(); // Retourne la liste complète
    }

    @PostMapping // POST /professeurs : ajoute un professeur
    public Professeur add(@RequestBody Professeur professeur) {
        return professeurRepository.save(professeur); // Enregistre en base
    }

    @DeleteMapping("/{id}") // DELETE /professeurs/1 : supprime un professeur
    public void delete(@PathVariable Long id) {
        professeurRepository.deleteById(id); // Supprime par ID
    }
}