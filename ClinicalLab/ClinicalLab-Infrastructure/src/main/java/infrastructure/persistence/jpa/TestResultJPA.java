package infrastructure.persistence.jpa;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "testResult")
public class TestResultJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long testResultId;

    @Column(name = "resultDate", nullable = false)
    private LocalDate resultDate;

    @Column(name = "resultContent")
    private String resultContent;

    public TestResultJPA(Long testResultId, LocalDate resultDate, String resultContent) {
        this.testResultId = testResultId;
        this.resultDate = resultDate;
        this.resultContent = resultContent;
    }

    public TestResultJPA() {}
}
