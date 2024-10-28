package infrastructure.persistence.jpa;

import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "examRequest")
public class ExamRequestJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long examRequestId;

    @ManyToOne
    @JoinColumn(name = "clientId", nullable = false)
    private Long clientId;

    @ElementCollection
    @CollectionTable(name = "examRequestTests", joinColumns = @JoinColumn(name = "examRequestId"))
    private List<Long> examTestList; // Alterado para Long

    @Column(name = "requestDate", nullable = false)
    private LocalDate requestDate;

    @Column(name = "totalPrice")
    private Double totalPrice;

    @Column(name = "paymentMethod", nullable = false)
    private String paymentMethod;

    @Column(name = "status", nullable = false)
    private String status;

    public ExamRequestJPA(Long examRequestId, Long clientId, List<Long> examTestList, LocalDate requestDate,
                          Double totalPrice, String paymentMethod, String status) {
        super();
        this.examRequestId = examRequestId;
        this.clientId = clientId;
        this.examTestList = examTestList;
        this.requestDate = requestDate;
        this.totalPrice = totalPrice;
        this.paymentMethod = paymentMethod;
        this.status = status;  
    }

    public ExamRequestJPA() {}
}
