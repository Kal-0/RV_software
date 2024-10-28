package infrastructure.persistence.jpa;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "TestResult")
public class TestResultJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long testResultId;

    @Column(name = "resultDate", nullable = false)
    private LocalDate resultDate;

    @Column(name = "resultContent")
    private String resultContent;

}
