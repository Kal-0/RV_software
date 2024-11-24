import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import domain.entities.examrequest.ExamRequest;
import domain.entities.examrequest.ExamRequestId;
import domain.entities.client.ClientId;
import domain.services.ExamRequestService;

import java.util.List;

@RestController
@RequestMapping("/exam-request")
public class ExamRequestController {

    @Autowired
    private ExamRequestService examRequestService;

    @PostMapping
    public ResponseEntity<ExamRequest> register(@RequestBody ExamRequest examRequest) {
        ExamRequest createdRequest = examRequestService.createExamRequest(examRequest);
        return ResponseEntity.status(201).body(createdRequest); // Retorna 201 Created
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExamRequest> getExamRequestById(@PathVariable("id") int id) {
        ExamRequestId examRequestId = new ExamRequestId(id);
        ExamRequest examRequest = examRequestService.findExamRequestById(examRequestId);
        return ResponseEntity.ok(examRequest);
    }

    @GetMapping
    public ResponseEntity<List<ExamRequest>> findAll() {
        List<ExamRequest> examRequests = examRequestService.getAllExamRequests();
        return ResponseEntity.ok(examRequests);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExamRequest> updateExamRequest(@PathVariable("id") int id, @RequestBody ExamRequest examRequest) {
        ExamRequestId examRequestId = new ExamRequestId(id);
        examRequest.setExamRequestId(examRequestId);
        ExamRequest updatedRequest = examRequestService.updateExamRequest(examRequestId, examRequest);
        return ResponseEntity.ok(updatedRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExamRequest(@PathVariable("id") int id) {
        ExamRequestId examRequestId = new ExamRequestId(id);
        examRequestService.deleteExamRequestById(examRequestId);
        return ResponseEntity.noContent().build(); // Retorna 204 No Content
    }
}
