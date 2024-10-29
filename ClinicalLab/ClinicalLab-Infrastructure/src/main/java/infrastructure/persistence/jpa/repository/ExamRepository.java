package infrastructure.persistence.jpa.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import infrastructure.persistence.jpa.ExamJPA;

public interface ExamRepository extends JpaRepository<ExamJPA, Long>{

}
