package org.project.core.database.service.classes;

import lombok.RequiredArgsConstructor;
import org.project.core.database.entity.ProcessParamsEntity;
import org.project.core.database.repository.ProcessParamsRepository;
import org.project.core.database.service.interfaces.ProcessParamsService;

import java.time.LocalDateTime;

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
        repository.save(entity);
    }

    @Override
    public void update(String name, String strVal, Double numberVal, LocalDateTime dateVal) {
        var param = findByName(name);
        param.setStringValue(strVal)
                .setNumberValue(numberVal)
                .setDateValue(dateVal);
        repository.save(param);
    }
}
