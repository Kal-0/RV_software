package infrastructure;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DecoyController {

    @GetMapping("/teste")
    public String teste() {
        return "A aplicação está rodando!";
    }
}
