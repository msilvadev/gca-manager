package br.com.gcamanager.services.assistance;

import br.com.gcamanager.domains.assistance.Assistance;
import br.com.gcamanager.domains.assistance.AssistanceDto;
import br.com.gcamanager.domains.assistance.AssistanceDtoBuilder;
import br.com.gcamanager.domains.assistance.AssistanceRepository;
import br.com.gcamanager.services.dashboard.DashboardReportService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssistanceManagerServiceImpl implements AssistanceManagerService {

    private final AssistanceRepository repository;
    private final DashboardReportService dashboardReportService;

    public AssistanceManagerServiceImpl(AssistanceRepository repository,
                                        DashboardReportService dashboardReportService) {
        this.repository = repository;
        this.dashboardReportService = dashboardReportService;
    }

    @Override
    public List<AssistanceDto> listAllAssistance() {
        List<Assistance> all = repository.findAll();

        return all.stream()
                .map(AssistanceDtoBuilder::build)
                .collect(Collectors.toList());
    }

    @Override
    public synchronized AssistanceDto saveAssistance(AssistanceDto assistanceDto) {
        AssistanceDto saved = AssistanceDtoBuilder.build(repository.save(AssistanceDtoBuilder
                .buildDtoToIndustrialProcess(assistanceDto)));

        dashboardReportService.updateCacheDashboarReport(saved);

        return saved;
    }

    @Override
    @Transactional
    public synchronized List<AssistanceDto> saveAssistance(List<AssistanceDto> assistanceDtoList) {
        List<Assistance> industrialProcessesSaved = repository.saveAll(assistanceDtoList.stream()
                .map(AssistanceDtoBuilder::buildDtoToIndustrialProcess)
                .collect(Collectors.toList()));

        return industrialProcessesSaved.stream()
                .map(AssistanceDtoBuilder::build)
                .collect(Collectors.toList());
    }

}
