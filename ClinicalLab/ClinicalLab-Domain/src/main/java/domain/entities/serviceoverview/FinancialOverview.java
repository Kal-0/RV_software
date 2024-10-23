package domain.entities.serviceoverview;

public class FinancialOverview {
    private int privatePayments;
    private int insurancePayments;
    private FinancialOverviewId financialOverviewId;

    // Construtor
    public FinancialOverview(int privatePayments, int insurancePayments, FinancialOverviewId financialOverviewId) {
        this.privatePayments = privatePayments;
        this.insurancePayments = insurancePayments;
        this.financialOverviewId = financialOverviewId;
    }

    // Getters e Setters
    public int getPrivatePayments() {
        return privatePayments;
    }

    public void setPrivatePayments(int privatePayments) {
        this.privatePayments = privatePayments;
    }

    public int getInsurancePayments() {
        return insurancePayments;
    }

    public void setInsurancePayments(int insurancePayments) {
        this.insurancePayments = insurancePayments;
    }

    public FinancialOverviewId getFinancialOverviewId() {
        return financialOverviewId;
    }

    public void setFinancialOverviewId(FinancialOverviewId financialOverviewId) {
        this.financialOverviewId = financialOverviewId;
    }
}

