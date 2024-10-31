package infrastructure.persistence.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Attendants")
public class AttendantJPA extends PersonJPA {
    private String password;
    

   
}