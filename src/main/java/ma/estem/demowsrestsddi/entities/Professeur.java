package ma.estem.demowsrestsddi.entities; // Package des entités

import jakarta.persistence.*; // Annotations JPA
import lombok.*; // Annotations Lombok

@Entity // Déclare la classe comme table
@Table(name = "PROFESSEUR") // Nom de la table dans MySQL
@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor // Génère les méthodes de base via Lombok
public class Professeur {

    @Id // Clé primaire
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrémentation
    @Column(name = "ID_PROFESSEUR") // Nom de la colonne clé primaire
    private Long idProfesseur; // Identifiant unique du professeur

    @Column(name = "NOM_PROFESSEUR") // Nom de la colonne pour le nom
    private String nomProfesseur; // Nom du professeur

    @Column(name = "SPECIALITE") // Nom de la colonne pour la spécialité
    private String specialite; // Domaine d'enseignement (ex: "Web Services")
}