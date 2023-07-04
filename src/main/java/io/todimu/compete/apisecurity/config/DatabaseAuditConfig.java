package io.todimu.compete.apisecurity.config;

import io.todimu.compete.apisecurity.security.SpringSecurityAuditorAwareImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("io.todimu.compete.apisecurity.repository")
@EnableJpaAuditing(auditorAwareRef = "springSecurityAuditorAwareImpl")
public class DatabaseAuditConfig {

    @Bean
    public AuditorAware<String> auditorAware() {
        return new SpringSecurityAuditorAwareImpl();
    }
}
