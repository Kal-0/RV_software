package domain.services;

import java.util.List;
import java.util.NoSuchElementException;

import domain.entities.attendant.Attendant;
import domain.entities.attendant.AttendantId;
import domain.entities.attendant.AttendantRepository;

public class AttendantService {

    private final AttendantRepository attendantRepository;

    public AttendantService(AttendantRepository attendantRepository) {
        this.attendantRepository = attendantRepository;
    }

 
    public Attendant save(Attendant attendant) {
        if (attendant == null) {
            throw new IllegalArgumentException("Attendant must not be null.");
        }

        if (attendantRepository.get(attendant.getAttendantId()) != null) {
            throw new IllegalArgumentException("An attendant with this ID already exists.");
        }

        attendantRepository.save(attendant);
        return attendantRepository.get(attendant.getAttendantId());
    }


    public Attendant getById(AttendantId id) {
        if (id == null) {
            throw new IllegalArgumentException("AttendantId must not be null.");
        }

        Attendant attendant = attendantRepository.get(id);
        if (attendant == null) {
            throw new NoSuchElementException("No attendant found with the given ID.");
        }
        return attendant;
    }


    public List<Attendant> getAll() {
        List<Attendant> attendants = attendantRepository.getAttendantAll();
        if (attendants == null || attendants.isEmpty()) {
            throw new NoSuchElementException("No attendants found.");
        }
        return attendants;
    }

    public void update(Attendant attendant) {
        if (attendant == null || attendant.getAttendantId() == null) {
            throw new IllegalArgumentException("Attendant and its ID must not be null.");
        }

        Attendant existingAttendant = attendantRepository.get(attendant.getAttendantId());
        if (existingAttendant == null) {
            throw new NoSuchElementException("No attendant found with the given ID.");
        }

        attendantRepository.update(attendant);
    }

  
    public void deleteById(AttendantId id) {
        if (id == null) {
            throw new IllegalArgumentException("AttendantId must not be null.");
        }

        Attendant existingAttendant = attendantRepository.get(id);
        if (existingAttendant == null) {
            throw new NoSuchElementException("No attendant found with the given ID.");
        }

        attendantRepository.delete(id);
    }
}
