package org.libra.data.services.interfaces;

import org.libra.data.entities.JobEntity;
import org.project.model.job.AddJobData;

import java.util.List;

public interface JobService {

    List<JobEntity> findAll();

    List<JobEntity> findAllActiveJobs();

    void save(AddJobData addJobData);

}
