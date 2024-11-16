package infrastructure.persistence.jpa.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import infrastructure.persistence.jpa.ExamRequestJPA;

public interface ExamRequestJPARepository extends JpaRepository<ExamRequestJPA, Integer>{

}
