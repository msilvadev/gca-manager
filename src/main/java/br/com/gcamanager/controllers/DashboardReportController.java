package br.com.gcamanager.controllers;

import br.com.gcamanager.domains.dashboard.DashboardAssistanceReportDto;
import br.com.gcamanager.services.dashboard.DashboardReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("gca-dashboard-report")
public class DashboardReportController {

    private final DashboardReportService dashboardReportService;

    public DashboardReportController(DashboardReportService dashboardReportService) {
        this.dashboardReportService = dashboardReportService;
    }

    @GetMapping
    public ResponseEntity<DashboardAssistanceReportDto> getDashboardReport() {
        return ResponseEntity.ok(dashboardReportService.getDashboardReport());
    }

    @PostMapping("refresh-cache")
    public ResponseEntity<DashboardAssistanceReportDto> refreshCache() {
        dashboardReportService.initializeCache();
        return ResponseEntity.ok(dashboardReportService.getDashboardReport());
    }
}
