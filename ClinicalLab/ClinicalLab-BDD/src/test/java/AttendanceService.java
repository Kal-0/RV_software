//package dev.sauloaraujo.sgb.acervo.dominio;

import static org.apache.commons.lang3.Validate.notNull;

import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.*;

import org.jmolecules.event.types.DomainEvent;


public class Client {
    private NotificationService notificationService;
    private Queue<Integer> attendanceNumbers;

    public AttendanceService(NotificationService notificationService) {
        this.notificationService = notificationService;
        this.attendanceNumbers = new LinkedList<>(); // Simulando números disponíveis
        this.attendanceNumbers.add(1);
        this.attendanceNumbers.add(2);  // Simular dois números disponíveis para teste
    }

    public boolean hasAvailableNumbers() {
        return !attendanceNumbers.isEmpty();
    }
}