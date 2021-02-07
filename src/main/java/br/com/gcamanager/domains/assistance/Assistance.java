package br.com.gcamanager.domains.assistance;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "ASSISTANCE")
public class Assistance implements Serializable {

    private static final long serialVersionUID = 7434897361855297228L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long number;

    @Enumerated(EnumType.STRING)
    @Column(name = "ASSISTANCE_TYPE")
    private AssistanceType assistanceType;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "START_DATE", columnDefinition = "TIMESTAMP")
    private LocalDateTime start;

    @Column(name = "END_DATE_TIME", columnDefinition = "TIMESTAMP")
    private LocalDateTime end;

    public Assistance(long number, AssistanceType assistanceType,
                      String description, LocalDateTime start) {
        this.number = number;
        this.assistanceType = assistanceType;
        this.description = description;
        this.start = start;
    }

    public Assistance() {
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public AssistanceType getAssistanceType() {
        return assistanceType;
    }

    public void setAssistanceType(AssistanceType assistanceType) {
        this.assistanceType = assistanceType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "Assistance{" +
                "number=" + number +
                ", assistanceType=" + assistanceType +
                ", description='" + description + '\'' +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
