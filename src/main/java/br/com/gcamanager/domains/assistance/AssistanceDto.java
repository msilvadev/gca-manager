package br.com.gcamanager.domains.assistance;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AssistanceDto implements Serializable {

    private static final long serialVersionUID = 3263614731673211493L;

    private long number;
    private int assistanceType;
    private String description;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime start;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime end;

    public AssistanceDto(long number, int assistanceType, String description,
                         LocalDateTime start, LocalDateTime end) {
        this.number = number;
        this.assistanceType = assistanceType;
        this.description = description;
        this.start = start;
        this.end = end;
    }

    public AssistanceDto() { }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public int getAssistanceType() {
        return assistanceType;
    }

    public void setAssistanceType(int assistanceType) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssistanceDto that = (AssistanceDto) o;
        return number == that.number && assistanceType == that.assistanceType && Objects.equals(description, that.description) && start.equals(that.start) && Objects.equals(end, that.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, assistanceType, description, start, end);
    }

    @Override
    public String toString() {
        return "AssistanceDto{" +
                "number=" + number +
                ", assistanceType=" + assistanceType +
                ", description='" + description + '\'' +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
