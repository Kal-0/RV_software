//package dev.sauloaraujo.sgb.acervo.dominio;

import static org.apache.commons.lang3.Validate.notNull;

import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.*;

import org.jmolecules.event.types.DomainEvent;


public class TestRequest {
    private Client client;
    private List<String> tests;
    private double totalCost;
    private boolean hasInsurance;

    public TestRequest(Client client, List<String> tests, boolean hasInsurance) {
        this.client = client;
        this.tests = tests;
        this.hasInsurance = hasInsurance;
    }

    public Client getClient() {
        return client;
    }

    public List<String> getTests() {
        return tests;
    }

    public void setTests(List<String> tests) {
        this.tests = tests;
    }

    public boolean hasInsurance() {
        return hasInsurance;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof TestRequest)) return false;
        TestRequest that = (TestRequest) obj;
        return client.getName().equals(that.client.getName()) && tests.equals(that.tests);
    }
}