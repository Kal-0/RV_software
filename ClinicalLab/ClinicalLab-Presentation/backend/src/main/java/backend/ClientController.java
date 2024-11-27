package backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import domain.entities.client.Client;
import domain.entities.client.ClientId;
import domain.services.ClientService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
//cai0
@RestController("controlador_cliente")
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<ClientDTO> save(@RequestBody ClientDTO clientDTO) {
        Client client = ClientMapper.toDomain(clientDTO);
        client = clientService.save(client);
        ClientDTO response = ClientMapper.toDTO(client);
        return ResponseEntity.status(201).body(response); // Retorna 201 Created
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
        List<Client> clients = clientService.getClientAll();
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
        public int id;
        public String cpf;
        public String contactEmail;
        public String name;
        public LocalDate birthDate;
        public int clientId;

        public ClientDTO() {}

        public ClientDTO(int id, String cpf, String contactEmail, String name, LocalDate birthDate, int clientId) {
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
            return new Client(
                dto.id,                         // Acessa diretamente o campo público
                dto.cpf,                        // Acessa diretamente o campo público
                dto.contactEmail,               // Acessa diretamente o campo público
                dto.name,                       // Acessa diretamente o campo público
                dto.birthDate.toString(),       // Converte LocalDate para String
                dto.clientId                    // Acessa diretamente o campo público
            );
        }

        public static ClientDTO toDTO(Client domain) {
            return new ClientDTO(
                domain.getId().getId(),          // Extrai o ID do domínio
                domain.getCpf().getCpf(),        // Extrai o CPF do domínio
                domain.getContactEmail().getEmailText(), // Extrai o Email do domínio
                domain.getName(),                // Extrai o nome do domínio
                domain.getBirthDate(),           // Passa o LocalDate diretamente
                domain.getClientId().getId()     // Extrai o ID do Client
            );
        }
    }
    
    
    
    
    
}
