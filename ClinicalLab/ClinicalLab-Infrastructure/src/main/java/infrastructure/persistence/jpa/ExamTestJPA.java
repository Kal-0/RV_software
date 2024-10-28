package infrastructure.persistence.jpa;

import jakarta.persistence.*;

@Entity
@Table(name = "examTest")
public class ExamTestJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long examTestId;

    @ManyToOne
    @JoinColumn(name = "examId", nullable = false)
    private Long examId;

    @ManyToOne
    @JoinColumn(name = "testResultId", nullable = false)
    private Long testResultId;

    @Column(name = "status")
    private String status;

    public ExamTestJPA(Long examTestId, Long examId, Long testResultId) {
        this.examTestId = examTestId;
        this.examId = examId;
        this.testResultId = testResultId;
    }

    public ExamTestJPA() {}
}
