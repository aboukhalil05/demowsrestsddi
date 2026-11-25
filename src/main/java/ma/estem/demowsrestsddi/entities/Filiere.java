package ma.estem.demowsrestsddi.entities; // Déclare le package auquel appartient cette classe

import com.fasterxml.jackson.annotation.JsonIgnore; // Permet d'ignorer un champ lors de la conversion en JSON
import jakarta.persistence.*; // Importe les annotations JPA (@Entity, @Table, @Id, etc.)
import lombok.*; // Importe les annotations Lombok (@Getter, @Setter, etc.)

import java.util.List; // Importe le type List pour contenir plusieurs étudiants

@Entity // Indique à JPA que cette classe est une entité (correspond à une table en base)
@Table(name = "FILIERE") // Force le nom de la table dans MySQL à "FILIERE"
@Getter // Lombok : génère automatiquement tous les getters
@Setter // Lombok : génère automatiquement tous les setters
@ToString // Lombok : génère automatiquement la méthode toString()
@NoArgsConstructor // Lombok : génère un constructeur vide (obligatoire pour JPA)
@AllArgsConstructor // Lombok : génère un constructeur avec tous les attributs
public class Filiere {

    @Id // Définit cet attribut comme la clé primaire de la table
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrémentation gérée par MySQL
    @Column(name = "ID_FILIERE") // Nom exact de la colonne dans MySQL
    private Long idFiliere; // Identifiant unique de la filière

    @Column(name = "CODE_FILIERE") // Nom de la colonne pour le code
    private String codeFiliere; // Code court de la filière (ex: "SDDI")

    @Column(name = "NOM_FILIERE") // Nom de la colonne pour l'intitulé
    private String nomFiliere; // Nom complet de la filière

    @OneToMany(mappedBy = "filiere") // Relation 1-N : une filière a plusieurs étudiants (gérée par l'attribut "filiere" dans Student)
    @JsonIgnore // Évite une boucle infinie lors de l'affichage JSON (Filiere -> Student -> Filiere...)
    private List<Student> students; // Liste des étudiants inscrits dans cette filière
}