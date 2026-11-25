package ma.estem.demowsrestsddi.web; // Package des contrôleurs REST

import lombok.AllArgsConstructor; // Injection par constructeur
import ma.estem.demowsrestsddi.entities.Module; // Entité Module
import ma.estem.demowsrestsddi.repositories.ModuleRepository; // Repository Module
import org.springframework.web.bind.annotation.*; // Annotations REST

import java.util.List; // Type List

@RestController // Contrôleur REST
@RequestMapping("/modules") // URL de base : http://localhost:8095/modules
@AllArgsConstructor // Injecte moduleRepository automatiquement
public class ModuleRest {

    private ModuleRepository moduleRepository; // Accès à la table MODULE

    @GetMapping // GET /modules : affiche tous les modules
    public List<Module> getAll() {
        return moduleRepository.findAll(); // Retourne tous les modules
    }

    @GetMapping("/filiere/{idFiliere}") // GET /modules/filiere/1 : modules d'une filière
    public List<Module> getByFiliere(@PathVariable Long idFiliere) {
        return moduleRepository.findByFiliereIdFiliere(idFiliere); // Filtre par ID de filière
    }

    @PostMapping // POST /modules : ajoute un module
    public Module add(@RequestBody Module module) {
        return moduleRepository.save(module); // Enregistre en base
    }

    @DeleteMapping("/{id}") // DELETE /modules/1 : supprime un module
    public void delete(@PathVariable Long id) {
        moduleRepository.deleteById(id); // Supprime par ID
    }
}