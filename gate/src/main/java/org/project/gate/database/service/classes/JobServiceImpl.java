package org.project.gate.database.service.classes;

import lombok.RequiredArgsConstructor;
import org.project.gate.database.entity.JobEntity;
import org.project.gate.database.repository.JobRepository;
import org.project.gate.database.service.interfaces.JobService;

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
    public JobEntity findByStockName(String name) {
        return jobRepository.findByStockName(name)
                .filter(Objects::nonNull)
                .orElse(null);
    }

    @Override
    public void save(String name, Long interval, String stock) {
        var job = new JobEntity()
                .setName(name)
                .setMillis(interval)
                .setStockName(stock);
        jobRepository.saveAndFlush(job);
    }
}
