package domain.common.entities.serviceoverview;

public interface PeriodRepository {
    
	void save(Period period);
    
    Period get();
    
}

