package org.project.data.services.interfaces;

import org.project.data.entities.ProcessParamsEntity;

import java.time.LocalDateTime;

public interface ProcessParamsService {

    ProcessParamsEntity findByName(String name);

    void save(ProcessParamsEntity entity);

    void update(String name, String strVal, Double numberVal, LocalDateTime dateVal);

}
