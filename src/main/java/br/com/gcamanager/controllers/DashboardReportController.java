package br.com.gcamanager.controllers;

import br.com.gcamanager.domains.dashboard.DashboardAssistanceReportDto;
import br.com.gcamanager.services.dashboard.DashboardReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ConcurrentMap;

@RestController
@RequestMapping("dashboard-report")
public class DashboardReportController {

    private final DashboardReportService dashboardReportService;

    public DashboardReportController(DashboardReportService dashboardReportService) {
        this.dashboardReportService = dashboardReportService;
    }

    @GetMapping
    public ResponseEntity<ConcurrentMap<Integer, DashboardAssistanceReportDto>> getDashboardReport() {
        return ResponseEntity.ok(dashboardReportService.getDashboardReport());
    }

    @PostMapping("refresh-cache")
    public ResponseEntity<ConcurrentMap<Integer, DashboardAssistanceReportDto>> refreshCache() {
        dashboardReportService.initializeCache();
        return ResponseEntity.ok(dashboardReportService.getDashboardReport());
    }
}
