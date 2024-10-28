package infrastructure.persistence.jpa;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "period")
public class PeriodJPA {

    @Column(name = "periodStart", nullable = false)
    private LocalDate periodStart;

    @Column(name = "periodEnd", nullable = false)
    private LocalDate periodEnd;

    public PeriodJPA(LocalDate periodStart, LocalDate periodEnd) {
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
    }

    public PeriodJPA() {}
}
