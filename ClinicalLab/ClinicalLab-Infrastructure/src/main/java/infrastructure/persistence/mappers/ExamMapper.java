package infrastructure.persistence.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import domain.entities.exam.Exam;
import infrastructure.persistence.jpa.ExamJPA;
import infrastructure.persistence.jpa.JPAMapper;

@Component
public class ExamMapper {

    @Autowired
    private JPAMapper jpaMapper;

    public Exam map(ExamJPA examJPA) {
        return jpaMapper.map(examJPA, Exam.class);
    }

    public ExamJPA map(Exam exam) {
        return jpaMapper.map(exam, ExamJPA.class);
    }
}
