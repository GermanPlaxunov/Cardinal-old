package org.project.data.services.interfaces;

import org.project.data.entities.JobEntity;

import java.util.List;

public interface JobService {

    List<JobEntity> findAll();

    JobEntity findByStockName(String name);

    void save(String name, Long interval, String stock);

}
