package br.com.gcamanager.domains.assistance;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AssistanceTest {

    @Test
    void constructor() {
        Assistance assistance = new Assistance();
        assertThat(assistance instanceof Assistance).isTrue();
    }

    @Test
    void getEnd() {
        AssistanceBuilder builder = new AssistanceBuilder();
        Assistance build = builder
                .withDescription("Test")
                .build();

        LocalDateTime end = LocalDateTime.now();
        build.setEnd(end);

        assertThat(build.getEnd()).isEqualTo(end);
    }

    @Test
    void industrialProcessToString() {
        AssistanceBuilder builder = new AssistanceBuilder();
        Assistance build = builder
                .withDescription("Test")
                .build();

        assertThat(build.toString()).isNotBlank();
    }
}