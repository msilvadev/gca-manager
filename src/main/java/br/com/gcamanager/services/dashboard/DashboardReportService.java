package br.com.gcamanager.services.dashboard;

import br.com.gcamanager.domains.assistance.AssistanceDto;
import br.com.gcamanager.domains.dashboard.DashboardAssistanceReportDto;

public interface DashboardReportService {

    void initializeCache();

    void updateCacheDashboarReport(AssistanceDto process);

    DashboardAssistanceReportDto getDashboardReport();
}
