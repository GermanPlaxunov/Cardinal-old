package org.project.gate.database.service.interfaces;

import org.project.gate.database.entity.JobEntity;

import java.util.List;

public interface JobService {

    List<JobEntity> findAll();

    JobEntity findByStockName(String name);

    void save(String name, Long interval, String stock);

}
