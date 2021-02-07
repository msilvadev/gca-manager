package br.com.gcamanager.domains.assistance;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AssistanceTypeTest {

    @Test
    void valueOfCode() {
        assertThat(AssistanceType.valueOfCode(1).getDescription()).isEqualTo(AssistanceType.CONSULTANCY.getDescription());
        assertThat(AssistanceType.valueOfCode(2).getDescription()).isEqualTo(AssistanceType.ADVISORY.getDescription());
        assertThat(AssistanceType.valueOfCode(3).getDescription()).isEqualTo(AssistanceType.DEFAULT.getDescription());
    }

    @Test
    void values() {
        assertThat(AssistanceType.values()).isNotNull();
    }

    @Test
    void valueOf() {
        assertThat(AssistanceType.valueOf("ADVISORY")).isEqualTo(AssistanceType.ADVISORY);
        assertThat(AssistanceType.valueOf("CONSULTANCY")).isEqualTo(AssistanceType.CONSULTANCY);
        assertThat(AssistanceType.valueOf("DEFAULT")).isEqualTo(AssistanceType.DEFAULT);
    }
}