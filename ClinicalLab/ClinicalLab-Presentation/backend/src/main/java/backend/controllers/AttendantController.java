package backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import domain.entities.attendant.Attendant;
import domain.entities.attendant.AttendantId;
import domain.services.AttendantService;
import infrastructure.persistence.jpa.AttendantJPA;
import infrastructure.persistence.jpa.JPAMapper;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/attendants")
public class AttendantController {

    @Autowired
    private JPAMapper mapper;

    @Autowired
    private AttendantService attendantService;


    @PostMapping
    public ResponseEntity<AttendantJPA> save(@RequestBody AttendantJPA attendantJPA) {
        Attendant attendant = mapper.map(attendantJPA, Attendant.class);
        Attendant savedAttendant = attendantService.save(attendant);
        AttendantJPA response = mapper.map(savedAttendant, AttendantJPA.class);
        return ResponseEntity.status(201).body(response); // Retorna 201 Created
    }

 
    @GetMapping("/{id}")
    public ResponseEntity<AttendantJPA> getById(@PathVariable("id") int id) {
        AttendantId attendantId = new AttendantId(id);
        Attendant attendant = attendantService.getById(attendantId);
        AttendantJPA response = mapper.map(attendant, AttendantJPA.class);
        return ResponseEntity.ok(response); // Retorna 200 OK
    }


    @GetMapping
    public ResponseEntity<List<AttendantJPA>> getAll() {
        List<Attendant> attendants = attendantService.getAll();
        List<AttendantJPA> response = new ArrayList<>();

        for (Attendant attendant : attendants) {
            response.add(mapper.map(attendant, AttendantJPA.class));
        }

        return ResponseEntity.ok(response); // Retorna 200 OK
    }


    @PutMapping
    public ResponseEntity<AttendantJPA> update(@RequestBody AttendantJPA attendantJPA) {
        Attendant attendant = mapper.map(attendantJPA, Attendant.class);
        attendantService.update(attendant);
        Attendant updatedAttendant = attendantService.getById(attendant.getAttendantId());
        AttendantJPA response = mapper.map(updatedAttendant, AttendantJPA.class);
        return ResponseEntity.ok(response); // Retorna 200 OK
    }

   
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") int id) {
        AttendantId attendantId = new AttendantId(id);
        attendantService.deleteById(attendantId);
        return ResponseEntity.noContent().build(); // Retorna 204 No Content
    }
}
