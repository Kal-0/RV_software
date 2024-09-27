//package dev.sauloaraujo.sgb.acervo.dominio;

import static org.apache.commons.lang3.Validate.notNull;

import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.*;

import org.jmolecules.event.types.DomainEvent;


public class PaymentService {
    public double calculateWithInsurance(int numberOfTests) {
        return numberOfTests * 50;  // Custo reduzido para clientes com convênio
    }

    public double calculateWithoutDiscount(int numberOfTests) {
        return numberOfTests * 100;  // Custo cheio para pagamento particular
    }
    
    public void completePayment(TestRequest request) {
        // Finalizar pagamento (simulação)
        System.out.println("Pagamento concluído para o cliente: " + request.getClient().getName());
    }
}