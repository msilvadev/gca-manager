package br.com.gcamanager.services.dashboard;

import br.com.gcamanager.domains.dashboard.DashboardAssistanceReportDto;
import br.com.gcamanager.domains.assistance.AssistanceDto;

import java.util.concurrent.ConcurrentMap;

public interface DashboardReportService {

    void initializeCache();

    void updateCacheDashboarReport(AssistanceDto process);

    ConcurrentMap<Integer, DashboardAssistanceReportDto> getDashboardReport();
}
