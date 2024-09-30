package domain.service;

import domain.common.entities.examtest.ExamTest;
import domain.common.entities.examtest.ExamTestId;
import domain.common.entities.examtest.ExamTestRepository;

public class ExamTestService {
	ExamTestRepository examTestRepository;

	public ExamTestService(ExamTestRepository examTestRepository) {
		super();
		this.examTestRepository = examTestRepository;
	}
	
	public void save(ExamTest examTest) {
		examTestRepository.save(examTest);
	}
	
	public void get(ExamTestId examTestId) {
		examTestRepository.get(examTestId);
	}
	
	public void update(ExamTest examTest) {
		examTestRepository.update(examTest);
	}
	
	public void delete(ExamTestId examTestId) {
		examTestRepository.delete(examTestId);
	}
	
}
