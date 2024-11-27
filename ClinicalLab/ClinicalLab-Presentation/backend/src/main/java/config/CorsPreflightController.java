package config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CorsPreflightController {

    @RequestMapping("/**")
    public ResponseEntity<Void> handlePreflight() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
