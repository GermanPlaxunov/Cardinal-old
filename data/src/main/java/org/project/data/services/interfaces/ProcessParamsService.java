package org.project.data.services.interfaces;

import org.project.data.entities.ProcessParamsEntity;

import java.util.List;

public interface ProcessParamsService {

    void save(ProcessParamsEntity entity);

    List<ProcessParamsEntity> findAllByNames(List<String> names);

}
