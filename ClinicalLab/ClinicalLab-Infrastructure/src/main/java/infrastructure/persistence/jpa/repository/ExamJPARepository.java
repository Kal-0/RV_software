package infrastructure.persistence.jpa.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import infrastructure.persistence.jpa.ExamJPA;

public interface ExamJPARepository extends JpaRepository<ExamJPA, Integer>{

}