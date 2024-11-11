import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import domain.entities.clientservice.ClientServiceId;
import domain.entities.clientservice.ClientServices;
import domain.entities.examrequest.ExamRequest;
import domain.entities.examrequest.ExamRequestId;
import domain.services.ClientServiceService;

@RestController
@RequestMapping("/client-service")
public class ClientServiceController {

    @Autowired
    private ClientServiceService clientServiceService;

    @PostMapping
    public ResponseEntity<ClientServices> register(@RequestBody ClientServices clientService) {
        ClientServices createdService = clientServiceService.save(clientService);
        return ResponseEntity.status(201).body(createdService); // Retorna 201 Created
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientServices> getClientServiceById(@PathVariable("id") int id) {
        ClientServiceId clientServiceId = new ClientServiceId(id);
        ClientServices clientService = clientServiceService.getClientServiceById(clientServiceId);
        return ResponseEntity.ok(clientService);
    }

    @PutMapping("/{id}/exam-request")
    public ResponseEntity<ClientServices> addExamRequestToClientService(
            @PathVariable("id") int id, @RequestBody ExamRequest examRequest) {
        ClientServiceId clientServiceId = new ClientServiceId(id);
        ClientServices clientService = clientServiceService.getClientServiceById(clientServiceId);
        clientServiceService.addExamRequest(clientService, examRequest);

