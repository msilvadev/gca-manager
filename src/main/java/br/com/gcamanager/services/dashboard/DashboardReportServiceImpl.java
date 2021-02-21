package br.com.gcamanager.services.dashboard;

import br.com.gcamanager.domains.assistance.AssistanceDto;
import br.com.gcamanager.domains.assistance.AssistanceRepository;
import br.com.gcamanager.domains.assistance.AssistanceType;
import br.com.gcamanager.domains.dashboard.DashboardAssistanceReportDto;
import br.com.gcamanager.domains.dashboard.DashboardAssistanceReportDtoBuilder;
import br.com.gcamanager.domains.dashboard.DashboardAssistanceReportRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class DashboardReportServiceImpl implements DashboardReportService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DashboardReportServiceImpl.class);

    private final AssistanceRepository assistanceRepository;
    private final DashboardAssistanceReportRepository dashboardAssistanceReportRepository;

    public DashboardReportServiceImpl(AssistanceRepository assistanceRepository,
                                      DashboardAssistanceReportRepository dashboardAssistanceReportRepository) {
        this.assistanceRepository = assistanceRepository;
        this.dashboardAssistanceReportRepository = dashboardAssistanceReportRepository;
    }

    @Override
    public void initializeCache() {
        LOGGER.info("Loading Dashboard Report Cache...");

        long start = System.currentTimeMillis();

        this.dashboardAssistanceReportRepository.clearCache();

        this.addDashboardReportToCache();

        LOGGER.info("Dashboard Report Cache loaded in {} ms", System.currentTimeMillis() - start);
    }

    @Override
    public void updateCacheDashboarReport(AssistanceDto process) {
        dashboardAssistanceReportRepository.update(process);
    }

    @Override
    public DashboardAssistanceReportDto getDashboardReport() {
        return dashboardAssistanceReportRepository.getAllDashboardReports();
    }

    private void addDashboardReportToCache() {
        DashboardAssistanceReportDtoBuilder builder = new DashboardAssistanceReportDtoBuilder();

        DashboardAssistanceReportDto report = builder
                .setAdvisoryQuantity(assistanceRepository.countByProcessTypeAndProcessStatus(AssistanceType.ADVISORY))
                .setConsultancyQuantity(assistanceRepository.countByProcessTypeAndProcessStatus(AssistanceType.CONSULTANCY))
                .setDefaultQuantity(assistanceRepository.countByProcessTypeAndProcessStatus(AssistanceType.DEFAULT))
                .build();

        this.dashboardAssistanceReportRepository.add(report);
    }

    private CompletableFuture<Void> executeAllTasks(List<Runnable> tasks) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        return CompletableFuture.allOf(tasks.stream()
                .map(task -> CompletableFuture.runAsync(task, executor)).toArray(CompletableFuture[]::new));
    }

}
