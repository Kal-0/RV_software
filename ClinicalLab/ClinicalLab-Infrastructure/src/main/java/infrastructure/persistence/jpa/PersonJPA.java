package infrastructure.persistence.jpa;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table(name = "People")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class PersonJPA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cpf;
    private String contactEmail;
    private String name;
    private LocalDate birthDate;

    // Construtores, getters e setters
}
