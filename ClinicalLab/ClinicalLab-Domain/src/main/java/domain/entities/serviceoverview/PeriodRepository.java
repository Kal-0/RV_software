package domain.entities.serviceoverview;

public interface PeriodRepository {
    
	void save(Period period);
    
    Period get();
    
}

