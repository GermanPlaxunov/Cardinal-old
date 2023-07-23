package org.project.core.database.repository;


import org.project.core.database.entity.ProcessParamsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProcessParamsRepository extends JpaRepository<ProcessParamsEntity, Long> {

    Optional<ProcessParamsEntity> findByName(String name);

}
