package org.project.data.repositories;

import org.project.data.entities.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobRepository extends JpaRepository<JobEntity, Long> {

    Optional<JobEntity> findByStockName(String name);

}
