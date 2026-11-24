package ma.estem.demowsrestsddi.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "STUDENT")
@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class Student {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_STUDENT")
    private Long idStudent;

    @Column(name = "FN_STUDENT")
    private String firstNameStudent;

    @Column(name = "LN_STUDENT")
    private String lastNameStudent;
}