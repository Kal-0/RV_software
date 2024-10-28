package infrastructure.persistence.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "clients")
public class ClientServiceJPA {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long clientServiceId;
    private Long serviceNumberId;
    private Long examRequestId;
    private String status;
}
