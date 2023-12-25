package org.libra.bragi.services.classes;

import lombok.RequiredArgsConstructor;
import org.libra.bragi.repositories.JobRepository;
import org.libra.bragi.services.interfaces.JobService;
import org.libra.bragi.entities.JobEntity;
import org.project.model.job.AddJobData;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;

    @Override
    public List<JobEntity> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public List<JobEntity> findAllActiveJobs() {
        return jobRepository.findAllByIsActive(true)
                .stream()
                .filter(Objects::nonNull)
                .toList();
    }

    @Override
    public void save(AddJobData addJobData) {
        var job = new JobEntity()
                .setJobName(addJobData.getName())
                .setIntervalMillis(addJobData.getIntervalMillis())
                .setStockName(addJobData.getStockName())
                .setJobType(addJobData.getJobType())
                .setJobDescription(addJobData.getDescription())
                .setIsActive(addJobData.getIsActive())
                .setTriggerName(addJobData.getTriggerName())
                .setTriggerDescription(addJobData.getTriggerDescription());
        jobRepository.saveAndFlush(job);
    }
}
