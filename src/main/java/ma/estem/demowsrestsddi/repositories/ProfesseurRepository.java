package ma.estem.demowsrestsddi.repositories; // Package des repositories

import ma.estem.demowsrestsddi.entities.Professeur; // Importe l'entité Professeur
import org.springframework.data.jpa.repository.JpaRepository; // Interface CRUD de Spring Data JPA

// Interface DAO pour la table PROFESSEUR (toutes les méthodes CRUD sont héritées automatiquement)
public interface ProfesseurRepository extends JpaRepository<Professeur, Long> {
}