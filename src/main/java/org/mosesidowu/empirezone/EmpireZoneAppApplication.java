package org.mosesidowu.empirezone;

import org.mosesidowu.empirezone.config.EnvLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmpireZoneAppApplication {

    public static void main(String[] args) {
       EnvLoader.loadEnv();
        SpringApplication.run(EmpireZoneAppApplication.class, args);
    }
}
