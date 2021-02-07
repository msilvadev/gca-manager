package br.com.gcamanager.services.dashboard;

import br.com.gcamanager.domains.dashboard.DashboardAssistanceReportDto;
import br.com.gcamanager.domains.dashboard.DashboardAssistanceReportDtoBuilder;
import br.com.gcamanager.domains.dashboard.DashboardAssistanceReportRepository;
import br.com.gcamanager.domains.assistance.AssistanceDto;
import br.com.gcamanager.domains.assistance.AssistanceRepository;
import br.com.gcamanager.domains.assistance.AssistanceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class DashboardReportServiceImpl implements DashboardReportService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DashboardReportServiceImpl.class);

    private final AssistanceRepository assistanceRepository;
    private final DashboardAssistanceReportRepository dashboardAssistanceReportRepository;

    private final List<Runnable> tasks;

    public DashboardReportServiceImpl(AssistanceRepository assistanceRepository,
                                      DashboardAssistanceReportRepository dashboardAssistanceReportRepository) {
        this.assistanceRepository = assistanceRepository;
        this.dashboardAssistanceReportRepository = dashboardAssistanceReportRepository;

        tasks = Collections.singletonList(() -> {
            this.loadProcessTypeNewOrderDashboardReport();
            this.loadProcessTypeDeliveryTissueDashboardReport();
        });
    }

    @Override
    public void initializeCache() {
        LOGGER.info("Loading Dashboard Report Cache...");

        long start = System.currentTimeMillis();

        this.dashboardAssistanceReportRepository.clearCache();

        this.executeAllTasks(tasks).join();

        LOGGER.info("Dashboard Report Cache loaded in {} ms", System.currentTimeMillis() - start);

        LOGGER.info("Load {} Dashboard Report Cache", this.dashboardAssistanceReportRepository.size());
    }

    @Override
    public void updateCacheDashboarReport(AssistanceDto process) {
        dashboardAssistanceReportRepository.update(process);
    }

    @Override
    public ConcurrentMap<Integer, DashboardAssistanceReportDto> getDashboardReport() {
        return dashboardAssistanceReportRepository.getAllDashboardReports();
    }

    public void loadProcessTypeNewOrderDashboardReport() {
        addDashboardReportToCache(AssistanceType.ADVISORY);
    }

    public void loadProcessTypeDeliveryTissueDashboardReport() {
        addDashboardReportToCache(AssistanceType.CONSULTANCY);
    }

    private void addDashboardReportToCache(AssistanceType assistanceType) {
        DashboardAssistanceReportDtoBuilder builder = new DashboardAssistanceReportDtoBuilder();

        DashboardAssistanceReportDto report = builder
                .setAdvisoryQuantity(assistanceRepository.countByProcessTypeAndProcessStatus(AssistanceType.ADVISORY))
                .setConsultancyQuantity(assistanceRepository.countByProcessTypeAndProcessStatus(AssistanceType.CONSULTANCY))
                .setDefaultQuantity(assistanceRepository.countByProcessTypeAndProcessStatus(AssistanceType.DEFAULT))
                .build();

        this.dashboardAssistanceReportRepository.add(assistanceType, report);
    }

    private CompletableFuture<Void> executeAllTasks(List<Runnable> tasks) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        return CompletableFuture.allOf(tasks.stream()
                .map(task -> CompletableFuture.runAsync(task, executor)).toArray(CompletableFuture[]::new));
    }

}
