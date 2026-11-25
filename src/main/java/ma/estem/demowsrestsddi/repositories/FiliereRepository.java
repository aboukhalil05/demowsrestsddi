package ma.estem.demowsrestsddi.repositories; // Package des repositories

import ma.estem.demowsrestsddi.entities.Filiere; // Importe l'entité Filiere
import org.springframework.data.jpa.repository.JpaRepository; // Interface Spring Data JPA contenant le CRUD de base

// Hérite de JpaRepository<Entité, TypeDeLaCléPrimaire> : fournit save, findAll, findById, deleteById...
public interface FiliereRepository extends JpaRepository<Filiere, Long> {
    Filiere findByCodeFiliere(String codeFiliere); // Requête générée automatiquement par Spring : SELECT * FROM filiere WHERE code_filiere = ?
}