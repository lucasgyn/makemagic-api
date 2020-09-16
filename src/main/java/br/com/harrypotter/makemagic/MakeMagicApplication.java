package br.com.harrypotter.makemagic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

/**
 * @author Lucas de Oliveira
 * @since 14/09/2020
 */

@SpringBootApplication(scanBasePackages = "br.com.harrypotter.makemagic")
public class MakeMagicApplication {

    public static void main(final String[] args) {
        SpringApplication.run(MakeMagicApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(final RestTemplateBuilder builder) {
        return builder
                .setConnectTimeout(Duration.ofMillis(3000))
                .setReadTimeout(Duration.ofMillis(3000))
                .build();
    }
}