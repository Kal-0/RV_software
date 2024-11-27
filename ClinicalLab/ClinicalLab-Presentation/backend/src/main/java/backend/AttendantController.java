package backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import domain.entities.attendant.Attendant;
import domain.entities.attendant.AttendantId;
import domain.services.AttendantService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/attendants")
public class AttendantController {

    @Autowired
    private AttendantService attendantService;

    @PostMapping
    public ResponseEntity<AttendantDTO> save(@RequestBody AttendantDTO attendantDTO) {
        Attendant attendant = AttendantMapper.toDomain(attendantDTO);
        Attendant savedAttendant = attendantService.save(attendant);
        AttendantDTO response = AttendantMapper.toDTO(savedAttendant);
        return ResponseEntity.status(201).body(response); // Retorna 201 Created
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttendantDTO> getById(@PathVariable("id") int id) {
        AttendantId attendantId = new AttendantId(id);
        Attendant attendant = attendantService.getById(attendantId);
        AttendantDTO response = AttendantMapper.toDTO(attendant);
        return ResponseEntity.ok(response); // Retorna 200 OK
    }

    @GetMapping
    public ResponseEntity<List<AttendantDTO>> getAll() {
        List<Attendant> attendants = attendantService.getAll();
        List<AttendantDTO> response = new ArrayList<>();

        for (Attendant attendant : attendants) {
            response.add(AttendantMapper.toDTO(attendant));
        }

        return ResponseEntity.ok(response); // Retorna 200 OK
    }

    @PutMapping
    public ResponseEntity<AttendantDTO> update(@RequestBody AttendantDTO attendantDTO) {
        Attendant attendant = AttendantMapper.toDomain(attendantDTO);
        attendantService.update(attendant);
        Attendant updatedAttendant = attendantService.getById(attendant.getAttendantId());
        AttendantDTO response = AttendantMapper.toDTO(updatedAttendant);
        return ResponseEntity.ok(response); // Retorna 200 OK
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") int id) {
        AttendantId attendantId = new AttendantId(id);
        attendantService.deleteById(attendantId);
        return ResponseEntity.noContent().build(); // Retorna 204 No Content
    }

    /**
     * Classe DTO para representar Attendant
     */
    public static class AttendantDTO {
    	public int id;
    	public int attendantId;
        public String cpf;
        public String contactEmail;
        public String name;
        public LocalDate birthDate;
        public String password;

        public AttendantDTO() {}

        public AttendantDTO(int id, String cpf, String contactEmail, String name, LocalDate birthDate, int attendantId, String password) {
            this.id = id;
            this.cpf = cpf;
            this.contactEmail = contactEmail;
            this.name = name;
            this.birthDate = birthDate;
            this.attendantId = attendantId;
            this.password = password;
        }

    }

    /**
     * Classe Mapper para converter entre DTO e Entidade do Domínio
     */
    public static class AttendantMapper {

        public static Attendant toDomain(AttendantDTO dto) {
            return new Attendant(
                dto.id,                          // Acessa diretamente o campo público
                dto.cpf,                         // Acessa diretamente o campo público
                dto.contactEmail,                // Acessa diretamente o campo público
                dto.name,                        // Acessa diretamente o campo público
                dto.birthDate.toString(),        // Converte LocalDate para String
                dto.attendantId,                 // Acessa diretamente o campo público
                dto.password                     // Acessa diretamente o campo público
            );
        }

        public static AttendantDTO toDTO(Attendant domain) {
            return new AttendantDTO(
                domain.getId().getId(),          // Extrai o ID do domínio
                domain.getCpf().getCpf(),        // Extrai o CPF do domínio
                domain.getContactEmail().getEmailText(), // Extrai o Email do domínio
                domain.getName(),                // Extrai o nome do domínio
                domain.getBirthDate(),           // Passa o LocalDate diretamente
                domain.getAttendantId().getId(), // Extrai o ID do Attendant
                domain.getPassword()             // Extrai a senha do domínio
            );
        }
    }

}
