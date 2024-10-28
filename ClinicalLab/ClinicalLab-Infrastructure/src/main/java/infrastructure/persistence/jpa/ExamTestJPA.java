package infrastructure.persistence.jpa;

import domain.entities.exam.Exam;
import jakarta.persistence.*;

@Entity
@Table(name = "ExamTest")
public class ExamTestJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long examTestId;

    @ManyToOne
    @JoinColumn(name = "Exam_Id")
    private Exam exam;

    @OneToOne
    @JoinColumn(name = "TestResult_Id", nullable = true)
    private TestResultJPA testResult;

    @Column(name = "status")
    private String status;

}
