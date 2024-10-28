package infrastructure.persistence.jpa;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "clients")
public class ClientJPA extends PersonJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;


    
}
