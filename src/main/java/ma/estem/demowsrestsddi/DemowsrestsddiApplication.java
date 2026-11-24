package ma.estem.demowsrestsddi;

import lombok.NonNull;
import ma.estem.demowsrestsddi.entities.Student;
import ma.estem.demowsrestsddi.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemowsrestsddiApplication implements CommandLineRunner {

    @Autowired
    StudentRepository studentRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemowsrestsddiApplication.class, args);
    }

    @Override
    public void run(String @NonNull ... args) throws Exception {

        // 1. CREATE (Ajouter des étudiants)
        // studentRepository.save(new Student(null, "Ikram", "Alami"));
        //  studentRepository.save(new Student(null, "Taha", "Tazi"));

        // 2. READ (Afficher tous les étudiants ou un seul par son ID)
        // studentRepository.findAll().forEach(System.out::println);
        // System.out.println(studentRepository.findById(3L));

        // 3. DELETE (Supprimer l'étudiant avec l'ID 1)
        //studentRepository.deleteById(1L);

        // 4. UPDATE (Modifier l'étudiant avec l'ID 2)
         Student st = studentRepository.findById(2L).get();
         //st.setFirstNameStudent("Malika");
        // studentRepository.save(st);
        // Test  recherche par prénom
        //studentRepository.findByFirstNameStudent("Malika").forEach(System.out::println);

    }
}