package infrastructure;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import infrastructure.persistence.jpa.ServiceNumberJPA;

@RestController
public class DecoyController {

    @GetMapping("/teste")
    public String teste() {
//    	ServiceNumberJPA sn1 = new ServiceNumberJPA();
//    	sn1.isPriority = 
        return "A aplicação está rodando!";
        
    }
}
