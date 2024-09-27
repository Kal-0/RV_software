//package dev.sauloaraujo.sgb.acervo.dominio;

import static org.apache.commons.lang3.Validate.notNull;

import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.*;

import org.jmolecules.event.types.DomainEvent;


public class Client {
    private String name;
    private boolean hasInsurance;
    private boolean isPriority;
    private boolean validData;

    public Client(String name, boolean hasInsurance, boolean isPriority, boolean validData) {
        this.name = name;
        this.hasInsurance = hasInsurance;
        this.isPriority = isPriority;
        this.validData = validData;
    }

    public String getName() {
        return name;
    }

    public boolean hasInsurance() {
        return hasInsurance;
    }

    public boolean isPriority() {
        return isPriority;
    }

    public boolean isDataValid() {
        return validData;
    }
}