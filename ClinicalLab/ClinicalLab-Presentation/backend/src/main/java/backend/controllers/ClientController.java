package backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import domain.entities.client.Client;
import domain.entities.client.ClientId;
import domain.entities.person.Cpf;
import domain.entities.person.Email;
import domain.entities.person.PersonId;
import domain.services.ClientService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
//cai0
@RestController("controlador_cliente")
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<ClientDTO> save(@RequestBody ClientDTO clientDTO) {
        Client client = ClientMapper.toDomain(clientDTO);
        client = clientService.save(client);
        System.out.println(client.getName());
        ClientDTO response = ClientMapper.toDTO(client);
        return ResponseEntity.ok(response); // Retorna 201 Created
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable("id") int id) {
        ClientId clientId = new ClientId(id);
        Client client = clientService.getClientById(clientId);
        ClientDTO response = ClientMapper.toDTO(client);
        return ResponseEntity.ok(response);
    }

    @GetMapping("")
    public ResponseEntity<List<ClientDTO>> getAll() {
        List<Client> clients = clientService.getAll();
        List<ClientDTO> clientsDTO = new ArrayList<>();

        for (Client client : clients) {
            clientsDTO.add(ClientMapper.toDTO(client));
        }

        return ResponseEntity.ok(clientsDTO);
    }

    @PutMapping("")
    public ResponseEntity<ClientDTO> updateClient(@RequestBody ClientDTO clientDTO) {
        Client client = ClientMapper.toDomain(clientDTO);
        clientService.updateClient(client);
        client = clientService.getClientById(client.getClientId());
        ClientDTO response = ClientMapper.toDTO(client);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClientDTO> deleteClient(@PathVariable("id") int id) {
        ClientId clientId = new ClientId(id);
        Client client = clientService.getClientById(clientId);
        ClientDTO response = ClientMapper.toDTO(client);
        clientService.deleteClientById(clientId);
        return ResponseEntity.ok(response);
    }

    /**
     * Classe DTO para representar Client
     */
    public static class ClientDTO {
        public Integer id;
        public String cpf;
        public String contactEmail;
        public String name;
        public String birthDate;
        public Integer clientId;

        public ClientDTO() {}

        public ClientDTO(int id, String cpf, String contactEmail, String name, String birthDate, int clientId) {
            this.id = id;
            this.cpf = cpf;
            this.contactEmail = contactEmail;
            this.name = name;
            this.birthDate = birthDate;
            this.clientId = clientId;
        }
    }

    /**
     * Classe Mapper para converter entre DTO e Entidade do Domínio
     */
    public static class ClientMapper {

        public static Client toDomain(ClientDTO dto) {
        	PersonId personId;  
        	ClientId clientId;
            Cpf cpf = new Cpf(dto.cpf);
            Email contactEmail = new Email(dto.contactEmail);
            String name = dto.name;     
            LocalDate birthDate = LocalDate.parse(dto.birthDate);
        	
        	if(dto.id != null) {
            	personId = new PersonId(dto.id);
            }
            else {
            	personId = null;
            }
            
            if(dto.clientId != null) {
            	clientId = new ClientId(dto.clientId);
            }
            else {
            	clientId = null;
            }
 
        	
        	
            return new Client(personId, cpf, contactEmail, name, birthDate, clientId);
            
        }
        public static ClientDTO toDTO(Client domain) {
            return new ClientDTO(
                domain.getId().getId(),          // Extrai o ID do domínio
                domain.getCpf().getCpf(),        // Extrai o CPF do domínio
                domain.getContactEmail().getEmailText(), // Extrai o Email do domínio
                domain.getName(),                // Extrai o nome do domínio
                domain.getBirthDate().toString(),           // Passa o LocalDate diretamente
                domain.getClientId().getId()     // Extrai o ID do Client
            );
        }
    }
    
    
    
    
    
}
