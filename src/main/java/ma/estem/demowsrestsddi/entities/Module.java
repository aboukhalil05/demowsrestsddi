package ma.estem.demowsrestsddi.entities; // Package des entités

import jakarta.persistence.*; // Annotations JPA
import lombok.*; // Annotations Lombok

@Entity // Déclare la classe comme table
@Table(name = "MODULE") // Nom de la table dans MySQL
@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor // Génère getters, setters, toString et constructeurs
public class Module {

    @Id // Clé primaire
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrémentation
    @Column(name = "ID_MODULE") // Nom de la colonne clé primaire
    private Long idModule; // Identifiant unique du module

    @Column(name = "NOM_MODULE") // Nom de la colonne pour le nom du module
    private String nomModule; // Intitulé du module (ex: "Spring Boot REST")

    @Column(name = "COEFFICIENT") // Nom de la colonne pour le coefficient
    private double coefficient; // Poids du module pour le calcul de la moyenne pondérée

    @ManyToOne // Plusieurs modules appartiennent à une même filière
    @JoinColumn(name = "ID_FILIERE") // Clé étrangère vers la table FILIERE
    private Filiere filiere; // Filière à laquelle appartient ce module

    @ManyToOne // Plusieurs modules peuvent être enseignés par un même professeur
    @JoinColumn(name = "ID_PROFESSEUR") // Clé étrangère vers la table PROFESSEUR
    private Professeur professeur; // Professeur responsable du module
}