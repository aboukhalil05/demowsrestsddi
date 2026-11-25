package ma.estem.demowsrestsddi; // Package racine du projet

import lombok.NonNull; // Annotation Lombok indiquant que le paramètre ne peut pas être null
import ma.estem.demowsrestsddi.entities.*; // Importe toutes les entités (Filiere, Student, Professeur, Module, Note)
import ma.estem.demowsrestsddi.repositories.*; // Importe tous les repositories
import org.springframework.beans.factory.annotation.Autowired; // Permet l'injection de dépendances par Spring
import org.springframework.boot.CommandLineRunner; // Interface permettant d'exécuter du code au démarrage
import org.springframework.boot.SpringApplication; // Classe qui lance l'application Spring Boot
import org.springframework.boot.autoconfigure.SpringBootApplication; // Active la configuration automatique de Spring Boot

import java.time.LocalDate; // Pour créer des dates d'examen

@SpringBootApplication // Point d'entrée principal de l'application Spring Boot
public class DemowsrestsddiApplication implements CommandLineRunner {

    @Autowired StudentRepository studentRepository; // Injecte le repository des étudiants
    @Autowired FiliereRepository filiereRepository; // Injecte le repository des filières
    @Autowired ProfesseurRepository professeurRepository; // Injecte le repository des professeurs
    @Autowired ModuleRepository moduleRepository; // Injecte le repository des modules
    @Autowired NoteRepository noteRepository; // Injecte le repository des notes

    public static void main(String[] args) {
        SpringApplication.run(DemowsrestsddiApplication.class, args); // Démarre le serveur Tomcat et le contexte Spring
    }

    @Override // Méthode exécutée automatiquement juste après le démarrage de l'application
    public void run(String @NonNull ... args) throws Exception {

        // Sécurité : n'insère les données de test QUE si la table FILIERE est vide (évite les doublons au redémarrage)
        if (filiereRepository.count() == 0) {

            // 1. Création et sauvegarde de 2 filières
            Filiere f1 = filiereRepository.save(new Filiere(null, "SDDI", "Sciences de Données", null));
            Filiere f2 = filiereRepository.save(new Filiere(null, "GI", "Génie Informatique", null));

            // 2. Création et sauvegarde de 3 étudiants rattachés aux filières
            Student s1 = studentRepository.save(new Student(null, "Ikram", "Alami", "ikram@estem.ma", f1));
            Student s2 = studentRepository.save(new Student(null, "Taha", "Tazi", "taha@estem.ma", f1));
            Student s3 = studentRepository.save(new Student(null, "Malika", "Bennani", "malika@estem.ma", f2));

            // 3. Création et sauvegarde de 2 professeurs
            Professeur p1 = professeurRepository.save(new Professeur(null, "Pr. Benali", "Web Services"));
            Professeur p2 = professeurRepository.save(new Professeur(null, "Pr. Chraibi", "Base de Données"));

            // 4. Création et sauvegarde de 2 modules (chemin complet utilisé pour éviter le conflit avec java.lang.Module)
            ma.estem.demowsrestsddi.entities.Module m1 = moduleRepository.save(
                    new ma.estem.demowsrestsddi.entities.Module(null, "Spring Boot REST", 3.0, f1, p1));
            ma.estem.demowsrestsddi.entities.Module m2 = moduleRepository.save(
                    new ma.estem.demowsrestsddi.entities.Module(null, "MySQL Avancé", 2.0, f1, p2));

            // 5. Enregistrement des notes pour Ikram (s1) et Taha (s2)
            noteRepository.save(new Note(null, 16.5, LocalDate.of(2026, 11, 20), s1, m1)); // Note Spring Boot d'Ikram
            noteRepository.save(new Note(null, 14.0, LocalDate.of(2026, 11, 22), s1, m2)); // Note MySQL d'Ikram
            noteRepository.save(new Note(null, 18.0, LocalDate.of(2026, 11, 20), s2, m1)); // Note Spring Boot de Taha
            noteRepository.save(new Note(null, 15.0, LocalDate.of(2026, 11, 22), s2, m2)); // Note MySQL de Taha
        }
    }
}