package org.project.gate.config;

import org.project.data.config.DataBeansConfig;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@Import(DataBeansConfig.class)
@EntityScan(basePackages = "org.project.data.entities")
@EnableJpaRepositories(basePackages = "org.project.data.repositories")
public class GateBeansConfig {


}