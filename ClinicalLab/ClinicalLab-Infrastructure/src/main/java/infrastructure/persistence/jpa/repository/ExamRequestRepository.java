package infrastructure.persistence.jpa.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import infrastructure.persistence.jpa.ExamRequestJPA;

public interface ExamRequestRepository extends JpaRepository<ExamRequestJPA, Long>{

}
