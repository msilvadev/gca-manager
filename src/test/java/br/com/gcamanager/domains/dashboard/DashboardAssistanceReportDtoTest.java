package br.com.gcamanager.domains.dashboard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DashboardAssistanceReportDtoTest {

    private DashboardAssistanceReportDto dashboardAssistanceReportDto;

    @BeforeEach
    void setUp() {
        dashboardAssistanceReportDto = new DashboardAssistanceReportDto(1, 1, 1);
    }

    @Test
    void getAdvisoryQuantity() {
        assertThat(dashboardAssistanceReportDto.getAdvisoryQuantity()).isEqualTo(1);
    }

    @Test
    void getConsultancyQuantity() {
        assertThat(dashboardAssistanceReportDto.getConsultancyQuantity()).isEqualTo(1);
    }

    @Test
    void getDefaultQuantity() {
        assertThat(dashboardAssistanceReportDto.getDefaultQuantity()).isEqualTo(1);
    }
}