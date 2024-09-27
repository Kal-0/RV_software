//package dev.sauloaraujo.sgb.acervo.dominio;

import static org.apache.commons.lang3.Validate.notNull;

import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.*;

import org.jmolecules.event.types.DomainEvent;


public class NotificationService {
    private String lastMessage;

    public void notifySuccess(String message) {
        this.lastMessage = message;
    }

    public void notifyError(String message) {
        this.lastMessage = message;
    }

    public String getLastMessage() {
        return lastMessage;
    } 
}