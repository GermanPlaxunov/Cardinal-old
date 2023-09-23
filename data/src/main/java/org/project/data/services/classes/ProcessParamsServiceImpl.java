package org.project.data.services.classes;

import lombok.RequiredArgsConstructor;
import org.project.data.entities.ProcessParamsEntity;
import org.project.data.repositories.ProcessParamsRepository;
import org.project.data.services.interfaces.ProcessParamsService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class ProcessParamsServiceImpl implements ProcessParamsService {

    private final ProcessParamsRepository repository;

    @Override
    public ProcessParamsEntity findByName(String name) {
        return repository.findByName(name)
                .orElse(null);
    }

    @Override
    public void save(ProcessParamsEntity entity) {
        repository.saveAndFlush(entity);
    }

    @Override
    public void update(String name, String strVal, Double numberVal, LocalDateTime dateVal) {
        var param = findByName(name);
        param.setStringValue(strVal)
                .setNumberValue(numberVal)
                .setDateValue(dateVal);
        repository.saveAndFlush(param);
    }

    @Override
    public List<ProcessParamsEntity> findAllByNames(List<String> names) {
        return repository.findAllByNameIn(names)
                .stream()
                .filter(Objects::nonNull)
                .toList();
    }
}
