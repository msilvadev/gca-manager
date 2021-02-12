package br.com.gcamanager.services;

import br.com.gcamanager.domains.assistance.*;
import br.com.gcamanager.services.dashboard.DashboardReportService;
import br.com.gcamanager.services.assistance.AssistanceManagerService;
import br.com.gcamanager.services.assistance.AssistanceManagerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

class AssistanceManagerServiceImplTest {

    @InjectMocks
    private AssistanceManagerService service;

    private AssistanceDto dto;
    private Assistance process;
    private final LocalDateTime start = LocalDateTime.now();
    private final List<AssistanceDto> listToSave = new ArrayList<>();
    private final List<Assistance> listSaved = new ArrayList<>();

    @BeforeEach
    void setUp() {
        AssistanceBuilder builder = new AssistanceBuilder();
        process = builder.withAssistanceType(AssistanceType.DEFAULT).withDescription("Test").build();
        listSaved.add(process);

        dto = new AssistanceDto(0, 3, "Test", start, null);
        listToSave.add(dto);

        AssistanceRepository repository = Mockito.mock(AssistanceRepository.class);
        DashboardReportService dashboardReportService = Mockito.mock(DashboardReportService.class);

        service = new AssistanceManagerServiceImpl(repository, dashboardReportService);

        when(repository.save(Mockito.any(Assistance.class))).thenReturn(process);
        when(repository.saveAll(Mockito.any(Iterable.class))).thenReturn(listSaved);
        when(repository.findAll()).thenReturn(listSaved);
    }

    @Test
    void listAllProcesses() {
        List<AssistanceDto> result = service.listAllAssistance();

        assertThat(result.size()).isEqualTo(listToSave.size());
        assertThat(result.get(0).getNumber()).isEqualTo(listToSave.get(0).getNumber());
    }

    @Test
    void saveIndustrialProcess() {
        AssistanceDto result = service.saveAssistance(dto);

        assertThat(result.getNumber()).isEqualTo(process.getNumber());
        assertThat(result.getAssistanceType()).isEqualTo(process.getAssistanceType().getCode());
        assertThat(result.getDescription()).isEqualTo(process.getDescription());
        assertThat(result.getStart()).isEqualTo(process.getStart());
    }

    @Test
    void testSaveIndustrialProcess() {
        List<AssistanceDto> result = service.saveAssistance(listToSave);

        assertThat(result.size()).isEqualTo(listToSave.size());
        assertThat(result.get(0).getNumber()).isEqualTo(listToSave.get(0).getNumber());
    }
}