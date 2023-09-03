package org.project.data.services.classes.neural;

import lombok.RequiredArgsConstructor;
import org.project.data.entities.neural.NeuralNetworkEntity;
import org.project.data.repositories.neural.NeuralNetworkRepository;
import org.project.data.services.interfaces.neural.NeuralNetworkService;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class NeuralNetworkServiceImpl implements NeuralNetworkService {

    private final NeuralNetworkRepository repository;

    @Override
    public void save(NeuralNetworkEntity entity) {
        repository.save(entity);
    }

    @Override
    public List<NeuralNetworkEntity> findAllBySymbol(String symbol) {
        return repository.findAllBySymbol(symbol)
                .stream()
                .filter(Objects::nonNull)
                .toList();
    }
}
