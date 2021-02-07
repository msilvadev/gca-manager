package br.com.gcamanager.domains.assistance;

import java.time.LocalDateTime;

public class AssistanceBuilder {

    private long number;
    private AssistanceType assistanceType;
    private String description;
    private LocalDateTime start;

    public AssistanceBuilder() {
        this.number = 0;
        this.assistanceType = AssistanceType.DEFAULT;
        this.start = LocalDateTime.now();
    }

    public AssistanceBuilder withNumber(long number) {
        this.number = number;
        return this;
    }

    public AssistanceBuilder withAssistanceType(AssistanceType assistanceType) {
        this.assistanceType = assistanceType;
        return this;
    }

    public AssistanceBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public AssistanceBuilder withStart(LocalDateTime start) {
        this.start = start;
        return this;
    }

    public Assistance build() {
        return new Assistance(
                this.number,
                this.assistanceType,
                this.description,
                this.start
        );
    }
}
