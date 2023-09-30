package org.project.data.services.classes;

import lombok.RequiredArgsConstructor;
import org.project.data.entities.ProcessParamsEntity;
import org.project.data.repositories.ProcessParamsRepository;
import org.project.data.services.interfaces.ProcessParamsService;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class ProcessParamsServiceImpl implements ProcessParamsService {

    private final ProcessParamsRepository repository;

    @Override
    public void save(ProcessParamsEntity entity) {
        repository.saveAndFlush(entity);
    }

    @Override
    public List<ProcessParamsEntity> findAllByNames(List<String> names) {
        return repository.findAllByNameIn(names)
                .stream()
                .filter(Objects::nonNull)
                .toList();
    }
}
