package domain.entities.serviceoverview;

public interface FinancialOverviewRepository {
	
    void save(FinancialOverview financialOverview);
    
    void delete(FinancialOverviewId financialOverviewId);
    
    FinancialOverview get(FinancialOverviewId financialOverviewId);
    
    void update(FinancialOverview financialOverview);
    
}

