package infrastructure.persistence.jpa.repositories;

import domain.entities.exam.Exam;
import domain.entities.exam.ExamId;
import domain.entities.exam.ExamRepository;
import infrastructure.persistence.jpa.ExamJPA;
import infrastructure.persistence.jpa.repository.ExamJPARepository;
import infrastructure.persistence.mappers.ExamMapper; // Importar o ExamMapper
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ExamRepositoryImpl implements ExamRepository {

    @Autowired
    private ExamJPARepository examJPARepository;

    @Autowired
    private ExamMapper examMapper; // Usar o ExamMapper

    @Override
    public void save(Exam exam) {
        ExamJPA examJPA = examMapper.map(exam);
        examJPARepository.save(examJPA);
    }

    @Override
    public void delete(ExamId id) {
        examJPARepository.deleteById((long) id.getId());
    }

    @Override
    public Exam get(ExamId id) {
        ExamJPA examJPA = examJPARepository.findById((long) id.getId()).orElse(null);
        return examMapper.map(examJPA);
    }

    @Override
    public void update(Exam exam) {
        ExamJPA examJPA = examMapper.map(exam);
        examJPARepository.save(examJPA);
    }
}
