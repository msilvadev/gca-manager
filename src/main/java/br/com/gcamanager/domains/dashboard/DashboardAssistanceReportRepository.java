package br.com.gcamanager.domains.dashboard;

import br.com.gcamanager.domains.assistance.AssistanceDto;
import br.com.gcamanager.domains.assistance.AssistanceType;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

@Component
public class DashboardAssistanceReportRepository {

    private final DashboardAssistanceReportDto dashboardCache = new DashboardAssistanceReportDto();

    private final Map<Integer, Consumer<DashboardAssistanceReportDto>> incrementQuantityToProcessStatus = new HashMap<>();

    @PostConstruct
    public void initialize() {
        incrementQuantityToProcessStatus.put(AssistanceType.CONSULTANCY.getCode(),
                dashboardAssistanceReportDto -> dashboardAssistanceReportDto.setConsultancyQuantity(1));

        incrementQuantityToProcessStatus.put(AssistanceType.ADVISORY.getCode(),
                dashboardAssistanceReportDto -> dashboardAssistanceReportDto.setAdvisoryQuantity(1));

        incrementQuantityToProcessStatus.put(AssistanceType.DEFAULT.getCode(),
                dashboardAssistanceReportDto -> dashboardAssistanceReportDto.setDefaultQuantity(1));
    }

    public void add(DashboardAssistanceReportDto newReport) {
        if (Objects.nonNull(dashboardCache)) {
            if (newReport.getAdvisoryQuantity() > 0) {
                this.dashboardCache.setAdvisoryQuantity(newReport.getAdvisoryQuantity());
            }
            if (newReport.getDefaultQuantity() > 0) {
                this.dashboardCache.setDefaultQuantity(newReport.getDefaultQuantity());
            }
            if (newReport.getConsultancyQuantity() > 0) {
                this.dashboardCache.setConsultancyQuantity(newReport.getConsultancyQuantity());
            }
        }
    }

    public DashboardAssistanceReportDto getAllDashboardReports() {
        return dashboardCache;
    }

    public void update(AssistanceDto assistanceDto) {
        Integer assistanceType = Integer.valueOf(assistanceDto.getAssistanceType());

        if (Objects.nonNull(dashboardCache)) {
            incrementQuantityToProcessStatus.get(assistanceType).accept(dashboardCache);
        }
    }

    public void clearCache() {
        this.dashboardCache.setDefaultQuantity(0);
        this.dashboardCache.setAdvisoryQuantity(0);
        this.dashboardCache.setConsultancyQuantity(0);
    }

}
