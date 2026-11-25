package ma.estem.demowsrestsddi.repositories; // Package des repositories

import ma.estem.demowsrestsddi.entities.Module; // Importe l'entité Module
import org.springframework.data.jpa.repository.JpaRepository; // Interface CRUD de Spring Data JPA

import java.util.List; // Importe le type List

// Interface DAO pour la table MODULE
public interface ModuleRepository extends JpaRepository<Module, Long> {
    List<Module> findByFiliereIdFiliere(Long idFiliere); // Récupère tous les modules rattachés à une filière donnée
}