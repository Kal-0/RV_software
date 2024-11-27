package backend.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import domain.entities.client.Client;
import domain.entities.client.ClientId;
import domain.services.ClientService;
import infrastructure.persistence.jpa.ClientJPA;
import infrastructure.persistence.jpa.JPAMapper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
//cai
@RestController("controlador_cliente")
@RequestMapping("/client")
public class ClientController {
	@Autowired
	private JPAMapper mapper;
	
    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<ClientJPA> save(@RequestBody ClientJPA clientJPA) {
    	Client client = mapper.map(clientJPA, Client.class);
        client = clientService.save(client);
        clientJPA = mapper.map(client, ClientJPA.class);
        return ResponseEntity.status(201).body(clientJPA); // Retorna 201 Created
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientJPA> getClientById(@PathVariable("id") int id) {
        ClientId clientId = new ClientId(id);
        Client client = clientService.getClientById(clientId);
        ClientJPA clientJPA = mapper.map(client, ClientJPA.class);
        return ResponseEntity.ok(clientJPA);
    }

    @GetMapping("")
    public ResponseEntity<List<ClientJPA>> getAll() {
        List<Client> clients = clientService.getAll();
        List<ClientJPA> clientsJPA = new ArrayList<ClientJPA>();
        
        for (Client client : clients) {
        	clientsJPA.add(mapper.map(client, ClientJPA.class ));
		}
        		
        return ResponseEntity.ok(clientsJPA);
    }

    @PutMapping("")
    public ResponseEntity<ClientJPA> updateClient(@RequestBody ClientJPA clientJPA) {
    	Client client = mapper.map(clientJPA, Client.class);
        clientService.updateClient(client);
        client = clientService.getClientById(client.getClientId());
        clientJPA = mapper.map(client, ClientJPA.class);
        return ResponseEntity.ok(clientJPA);
    }

    
//    NUM TA FUNFANDO
    @DeleteMapping("/{id}")
    public ResponseEntity<ClientJPA> deleteClient(@PathVariable("id") int id) {
        ClientId clientId = new ClientId(id);
        Client client = clientService.getClientById(clientId);
        ClientJPA clientJPA = mapper.map(client, ClientJPA.class);
        clientService.deleteClientById(clientId);
        return ResponseEntity.ok(clientJPA); // Retorna 204 No Content
    }
    
    
    
    
    
}
