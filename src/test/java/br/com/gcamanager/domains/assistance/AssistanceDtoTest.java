package br.com.gcamanager.domains.assistance;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class AssistanceDtoTest {

    private AssistanceDto dto;
    private final LocalDateTime localDateTime = LocalDateTime.now();

    @BeforeEach
    void setUp() {
        dto = new AssistanceDto(1, 3, "Test",
                localDateTime, localDateTime);
    }

    @Test
    void getNumber() {
        assertThat(dto.getNumber()).isEqualTo(1);
    }

    @Test
    void setNumber() {
        dto.setNumber(12);
        assertThat(dto.getNumber()).isEqualTo(12);
    }

    @Test
    void getAssistanceType() {
        assertThat(dto.getAssistanceType()).isEqualTo(AssistanceType.DEFAULT.getCode());
    }

    @Test
    void setAssistanceType() {
        dto.setAssistanceType(AssistanceType.ADVISORY.getCode());
        assertThat(dto.getAssistanceType()).isEqualTo(AssistanceType.ADVISORY.getCode());
    }

    @Test
    void getDescription() {
        assertThat(dto.getDescription()).isEqualTo("Test");
    }

    @Test
    void setDescription() {
        dto.setDescription("Mock");
        assertThat(dto.getDescription()).isEqualTo("Mock");
    }

    @Test
    void getStart() {
        assertThat(dto.getStart()).isEqualTo(this.localDateTime);
    }

    @Test
    void setStart() {
        dto.setStart(this.localDateTime);
        assertThat(dto.getStart()).isEqualTo(this.localDateTime);
    }

    @Test
    void getEnd() {
        assertThat(dto.getEnd()).isEqualTo(this.localDateTime);
    }

    @Test
    void setEnd() {
        dto.setEnd(this.localDateTime);
        assertThat(dto.getEnd()).isEqualTo(this.localDateTime);
    }

    @Test
    void testEquals() {
        AssistanceDto dtoToEqual = new AssistanceDto(1, 3, "Test",
                localDateTime, localDateTime);

        assertThat(dto.equals(dtoToEqual)).isTrue();
    }

    @Test
    void testHashCode() {
        AssistanceDto dtoToHash = new AssistanceDto(1, 3, "Test",
                localDateTime, localDateTime);

        assertThat(dto.hashCode() == dtoToHash.hashCode()).isTrue();
    }
}