package com.skylink.gympro.configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Configuration
public class PaginationConfig {
    @Bean
    public Pageable defaultPageable() {
        return PageRequest.of(0, 10, Sort.by("id").ascending());
    }
}
