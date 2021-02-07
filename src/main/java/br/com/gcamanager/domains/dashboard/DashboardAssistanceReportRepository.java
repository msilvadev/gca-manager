package br.com.gcamanager.domains.dashboard;

import br.com.gcamanager.domains.assistance.AssistanceDto;
import br.com.gcamanager.domains.assistance.AssistanceType;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Consumer;

@Component
public class DashboardAssistanceReportRepository {

    private final ConcurrentHashMap<Integer, DashboardAssistanceReportDto> dashboardCache = new ConcurrentHashMap<>();

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

    public void add(AssistanceType assistanceType, DashboardAssistanceReportDto newReport) {
        DashboardAssistanceReportDto dashboardReportFound = this.getByProcessType(assistanceType);

        if (Objects.nonNull(dashboardReportFound)) {
            incrementQuantityToProcessStatus
                    .forEach((integer, dashboardReportDtoConsumer) ->
                            dashboardReportDtoConsumer.accept(dashboardReportFound));
        } else {
            dashboardCache.put(assistanceType.getCode(), newReport);
        }
    }

    public ConcurrentMap<Integer, DashboardAssistanceReportDto> getAllDashboardReports() {
        return dashboardCache;
    }

    public DashboardAssistanceReportDto getByProcessType(AssistanceType assistanceType) {
        return dashboardCache.get(assistanceType.getCode());
    }

    public void update(AssistanceDto assistanceDto) {
        DashboardAssistanceReportDto dashboardReportFound =
                this.getByProcessType(AssistanceType.valueOfCode(assistanceDto.getAssistanceType()));

        Integer assistanceType = Integer.valueOf(assistanceDto.getAssistanceType());

        if (Objects.nonNull(dashboardReportFound)) {
            incrementQuantityToProcessStatus.get(assistanceType).accept(dashboardReportFound);
        } else {
            DashboardAssistanceReportDto dashboardAssistanceReportDto = new DashboardAssistanceReportDto();

            dashboardCache.put(AssistanceType.valueOfCode(assistanceDto.getAssistanceType()).getCode(),
                    dashboardAssistanceReportDto);
        }
    }

    public int size() {
        return dashboardCache.size();
    }

    public void clearCache() {
        this.dashboardCache.clear();
    }

}
