package infrastructure.persistence.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Attendants")
public class AttendantJPA extends PersonJPA {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long attendantId;
    private String password;
    

   
}