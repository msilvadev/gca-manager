package br.com.gcamanager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class GcaManagerApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(GcaManagerApplication.class);

    public static void main(String[] args) {
        LOGGER.info("Initializing GpiManagerApplication Application...");

        new SpringApplicationBuilder()
                .bannerMode(Banner.Mode.LOG)
                .sources(GcaManagerApplication.class)
                .run(args);

        LOGGER.info("GpiManagerApplication has completed startup");
    }

}
