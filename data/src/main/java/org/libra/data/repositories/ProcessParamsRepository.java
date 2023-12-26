package org.libra.data.repositories;

import org.libra.data.entities.ProcessParamsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProcessParamsRepository extends JpaRepository<ProcessParamsEntity, Long> {

    Optional<ProcessParamsEntity> findByName(String name);

    List<ProcessParamsEntity> findAllByNameIn(List<String> names);

}
