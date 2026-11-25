package ma.estem.demowsrestsddi.entities; // Package des entités

import jakarta.persistence.*; // Annotations JPA pour le mapping objet-relationnel
import lombok.*; // Annotations Lombok pour éviter le code répétitif

@Entity // Déclare la classe comme table en base de données
@Table(name = "STUDENT") // Nom de la table dans MySQL
@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor // Génère getters, setters, toString et constructeurs
public class Student {

    @Id // Clé primaire
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrémentée par MySQL
    @Column(name = "ID_STUDENT") // Nom de la colonne clé primaire
    private Long idStudent; // Identifiant unique de l'étudiant

    @Column(name = "FN_STUDENT") // Nom de la colonne pour le prénom
    private String firstNameStudent; // Prénom de l'étudiant

    @Column(name = "LN_STUDENT") // Nom de la colonne pour le nom
    private String lastNameStudent; // Nom de famille de l'étudiant

    @Column(name = "EMAIL_STUDENT") // Nom de la colonne pour l'email
    private String emailStudent; // Adresse email de l'étudiant

    @ManyToOne // Relation N-1 : plusieurs étudiants appartiennent à une seule filière
    @JoinColumn(name = "ID_FILIERE") // Crée la colonne clé étrangère ID_FILIERE dans la table STUDENT
    private Filiere filiere; // Objet Filiere associé à cet étudiant
}