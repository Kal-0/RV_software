package backend.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import domain.entities.client.Client;
import domain.entities.client.ClientId;
import domain.services.ClientService;

import java.util.List;

@RestController("controlador_cliente")
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<Client> register(@RequestBody Client client) {
        Client createdClient = clientService.save(client);
        return ResponseEntity.status(201).body(createdClient); // Retorna 201 Created
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable("id") int id) {
        ClientId clientId = new ClientId(id);
        Client client = clientService.getClientById(clientId);
        return ResponseEntity.ok(client);
    }

//    @GetMapping
//    public ResponseEntity<List<Client>> findAll() {
//        List<Client> clients = clientService.getAllClients();
//        return ResponseEntity.ok(clients);
//    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Client> updateClient(@PathVariable("id") int id, @RequestBody Client client) {
//        ClientId clientId = new ClientId(id);
//        client.setClientId(clientId);
//        Client updatedClient = clientService.updateClient(client);
//        return ResponseEntity.ok(updatedClient);
//    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteClient(@PathVariable("id") int id) {
//        ClientId clientId = new ClientId(id);
//        clientService.deleteClientById(clientId);
//        return ResponseEntity.noContent().build(); // Retorna 204 No Content
//    }
}
