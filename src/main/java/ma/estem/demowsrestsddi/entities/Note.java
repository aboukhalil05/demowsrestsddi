package ma.estem.demowsrestsddi.entities; // Package des entités

import jakarta.persistence.*; // Annotations JPA
import lombok.*; // Annotations Lombok

import java.time.LocalDate; // Type Java moderne pour stocker une date (AAAA-MM-JJ)

@Entity // Déclare la classe comme table
@Table(name = "NOTE") // Nom de la table dans MySQL
@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor // Génère les méthodes de base via Lombok
public class Note {

    @Id // Clé primaire
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrémentation
    @Column(name = "ID_NOTE") // Nom de la colonne clé primaire
    private Long idNote; // Identifiant unique de la note

    @Column(name = "VALEUR") // Nom de la colonne pour la valeur de la note
    private double valeur; // Note obtenue sur 20

    @Column(name = "DATE_EXAMEN") // Nom de la colonne pour la date
    private LocalDate dateExamen; // Date à laquelle l'examen a eu lieu

    @ManyToOne // Plusieurs notes peuvent appartenir au même étudiant
    @JoinColumn(name = "ID_STUDENT") // Clé étrangère vers la table STUDENT
    private Student student; // Étudiant concerné par cette note

    @ManyToOne // Plusieurs notes peuvent concerner le même module
    @JoinColumn(name = "ID_MODULE") // Clé étrangère vers la table MODULE
    private Module module; // Module concerné par cette note
}