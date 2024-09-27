//package dev.sauloaraujo.sgb.acervo.dominio;

import static org.apache.commons.lang3.Validate.notNull;

import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.*;

import org.jmolecules.event.types.DomainEvent;


public class ClientService {
    private NotificationService notificationService;

    public ClientService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void registerClient(Client client) {
        if (client.getName().isEmpty() || !client.isDataValid()) {
            throw new IllegalArgumentException("Invalid client data.");
        }
        // Registrar o cliente no sistema (simulado)
        notificationService.notifySuccess("Customer successfully registered!");
    }
}