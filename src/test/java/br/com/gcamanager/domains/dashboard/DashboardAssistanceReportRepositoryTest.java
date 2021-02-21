package br.com.gcamanager.domains.dashboard;

import br.com.gcamanager.domains.assistance.AssistanceDto;
import br.com.gcamanager.domains.assistance.AssistanceType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class DashboardAssistanceReportRepositoryTest {

    private DashboardAssistanceReportRepository repository;
    private final AssistanceType assistanceType = AssistanceType.DEFAULT;
    private DashboardAssistanceReportDto dto;
    private AssistanceDto assistanceDto;

    @BeforeEach
    void setUp() {
        repository = new DashboardAssistanceReportRepository();
        repository.initialize();

        DashboardAssistanceReportDtoBuilder builder = new DashboardAssistanceReportDtoBuilder();
        this.dto = builder
                .setAdvisoryQuantity(1)
                .setConsultancyQuantity(1)
                .setDefaultQuantity(1)
                .build();

        repository.add(this.dto);

        assistanceDto = new AssistanceDto(1, 3, "Test", LocalDateTime.now(), LocalDateTime.now());
    }

    @Test
    void add() {
        repository.add(this.dto);

        assertThat(repository.getAllDashboardReports()).isNotNull();
    }

    @Test
    void getAllDashboardReports() {
        assertThat(repository.getAllDashboardReports()).isNotNull();
    }

    @Test
    void update() {
        repository.update(assistanceDto);

        DashboardAssistanceReportDto result = repository.getAllDashboardReports();
        assertThat(result.getDefaultQuantity()).isEqualTo(1);
    }

    @Test
    void tryUpdateWhenNotExistYet() {
        repository.update(assistanceDto);

        DashboardAssistanceReportDto result = repository.getAllDashboardReports();

        assertThat(result.getDefaultQuantity()).isEqualTo(1);
    }
}