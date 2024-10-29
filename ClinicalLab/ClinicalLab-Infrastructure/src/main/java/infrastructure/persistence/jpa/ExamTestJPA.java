package infrastructure.persistence.jpa;

import jakarta.persistence.*;

@Entity
@Table(name = "Exam_Test")
public class ExamTestJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long examTestId;

    @ManyToOne
    @JoinColumn(name = "Exam_Id")
    private ExamJPA exam;

    @OneToOne
    @JoinColumn(name = "TestResult_Id", nullable = true)
    private TestResultJPA testResult;

    @Column(name = "status")
    private String status;

}
