package br.com.gcamanager.domains.assistance;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class AssistanceBuilderTest {

    private AssistanceBuilder builder;
    private final LocalDateTime start = LocalDateTime.now();

    @BeforeEach
    void setUp() {
        builder = new AssistanceBuilder();
    }

    @Test
    void withNumber() {
        Assistance build = builder.withNumber(1).build();
        assertThat(build.getNumber()).isEqualTo(1);
    }

    @Test
    void withProcessType() {
        Assistance build = builder.withAssistanceType(AssistanceType.DEFAULT).build();
        assertThat(build.getAssistanceType()).isEqualTo(AssistanceType.DEFAULT);
    }

    @Test
    void withDescription() {
        Assistance build = builder.withDescription("Test").build();
        assertThat(build.getDescription()).isEqualTo("Test");
    }

    @Test
    void withStart() {
        Assistance build = builder.withStart(this.start).build();
        assertThat(build.getStart()).isEqualTo(this.start);
    }

    @Test
    void build() {
        Assistance build = builder.withNumber(1)
                .withAssistanceType(AssistanceType.DEFAULT)
                .withDescription("Test")
                .withStart(this.start)
                .build();

        assertThat(build).isNotNull();
        assertThat(build.getNumber()).isEqualTo(1);
        assertThat(build.getAssistanceType()).isEqualTo(AssistanceType.DEFAULT);
        assertThat(build.getDescription()).isEqualTo("Test");
        assertThat(build.getStart()).isEqualTo(this.start);
        assertThat(build.getEnd()).isNull();
    }

    @Test
    void shouldBeBuildWithDefaultValuesNumberProcessTypeProcessStatusAndStart() {
        Assistance build = builder
                .withAssistanceType(AssistanceType.DEFAULT)
                .withDescription("Test")
                .build();

        assertThat(build).isNotNull();
        assertThat(build.getNumber()).isZero();
        assertThat(build.getAssistanceType()).isEqualTo(AssistanceType.DEFAULT);
        assertThat(build.getDescription()).isEqualTo("Test");
        assertThat(build.getStart()).isNotNull();
    }
}