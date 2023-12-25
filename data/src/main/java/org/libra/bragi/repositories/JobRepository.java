package org.libra.bragi.repositories;

import org.libra.bragi.entities.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<JobEntity, Long> {

    List<JobEntity> findAllByIsActive(boolean isActive);

}
