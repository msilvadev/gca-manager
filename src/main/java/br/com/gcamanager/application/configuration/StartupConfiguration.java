package br.com.gcamanager.application.configuration;

import br.com.gcamanager.domains.assistance.AssistanceDto;
import br.com.gcamanager.services.dashboard.DashboardReportService;
import br.com.gcamanager.services.assistance.AssistanceManagerService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Configuration
@EnableJpaRepositories(basePackages = "br.com.gcamanager.domains")
public class StartupConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(StartupConfiguration.class);

    private final DashboardReportService dashboardReportService;

    public StartupConfiguration(DashboardReportService dashboardReportService) {
        this.dashboardReportService = dashboardReportService;
    }

    @Bean("initialLoad")
    CommandLineRunner initialLoad(AssistanceManagerService service) {
        return args -> {
            // read json file and write to db
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<AssistanceDto>> typeReference = new TypeReference<List<AssistanceDto>>(){};
            InputStream inputStream = TypeReference.class.getResourceAsStream("/json/assistance.json");
            try {
                List<AssistanceDto> processDtos = mapper.readValue(inputStream,typeReference);
                service.saveAssistance(processDtos);

                dashboardReportService.initializeCache();

                LOGGER.info("Load json with mock data to test!");
            } catch (IOException e){
                LOGGER.info("Failed to load json data to test from /json/assistance.json, {}", e.getMessage());
            }
        };
    }
}
