//package dev.sauloaraujo.sgb.acervo.dominio;

import static org.apache.commons.lang3.Validate.notNull;

import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.*;

import org.jmolecules.event.types.DomainEvent;


public class QueueService {
    public void addToQueue(Client client) {
        // Simula adição à fila de coleta
        System.out.println("Cliente " + client.getName() + " adicionado à fila de coleta.");
    }
}