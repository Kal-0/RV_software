package infrastructure.persistence.jpa;

import jakarta.persistence.*;

@Entity
@Table(name = "financialOverview")
public class FinancialOverviewJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long financialOverviewId;

    @Column(name = "privatePayments", nullable = false)
    private int privatePayments;

    @Column(name = "insurancePayments", nullable = false)
    private int insurancePayments;

    public FinancialOverviewJPA(int privatePayments, int insurancePayments, Long financialOverviewId) {
        this.privatePayments = privatePayments;
        this.insurancePayments = insurancePayments;
        this.financialOverviewId = financialOverviewId;
    }

    public FinancialOverviewJPA() {}
}
