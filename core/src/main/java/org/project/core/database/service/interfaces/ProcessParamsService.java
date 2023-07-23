package org.project.core.database.service.interfaces;

import org.project.core.database.entity.ProcessParamsEntity;

import java.time.LocalDateTime;

public interface ProcessParamsService {

    ProcessParamsEntity findByName(String name);

    void save(ProcessParamsEntity entity);

    void update(String name, String strVal, Double numberVal, LocalDateTime dateVal);

}
