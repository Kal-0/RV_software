package domain.services.interfaces;

import domain.entities.examrequest.ExamRequestId;

public interface PayamentStrategy {

	Double calculateTotalPrice(ExamRequestId examRequestId);
}
