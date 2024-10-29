package infrastructure.persistence.jpa.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import infrastructure.persistence.jpa.ExamTestJPA;

public interface ExamTestRepository extends JpaRepository<ExamTestJPA, Long>{

}
