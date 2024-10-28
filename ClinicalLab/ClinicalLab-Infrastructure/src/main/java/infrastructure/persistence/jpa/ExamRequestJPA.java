package infrastructure.persistence.jpa;

import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "ExamRequest")
public class ExamRequestJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long examRequestId;

    @ManyToOne
    @JoinColumn(name = "Client_Id")
    private ClientJPA client;

    @OneToMany
    @JoinColumn(name = "Service_Number_Id")
    private List<ExamTestJPA> examTestList;

    @Column(name = "requestDate", nullable = false)
    private LocalDate requestDate;

    @Column(name = "totalPrice")
    private Double totalPrice;

    @Column(name = "paymentMethod", nullable = false)
    private String paymentMethod;

    @Column(name = "status", nullable = false)
    private String status;

}
