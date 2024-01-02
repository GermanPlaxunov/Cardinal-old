package org.cardinal.data.services.classes.neural;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.cardinal.data.repositories.neural.NeuralNetworkRepository;
import org.cardinal.data.services.interfaces.neural.NeuralNetworkService;
import org.cardinal.data.entities.neural.NeuralNetworkEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class NeuralNetworkServiceImpl implements NeuralNetworkService {

    private final NeuralNetworkRepository repository;

    @Override
    @Transactional
    public void save(String symbol, String name, String vector) {
        var network = repository.findAllByName(name)
                .orElse(new NeuralNetworkEntity());
        repository.saveAndFlush(network
                .setSymbol(symbol)
                .setName(name)
                .setVector(vector)
                .setTrainDate(LocalDateTime.now()));
    }

    @Override
    @Transactional
    public List<NeuralNetworkEntity> findAllBySymbol(String symbol) {
        return repository.findAllBySymbol(symbol)
                .stream()
                .filter(Objects::nonNull)
                .toList();
    }

    @Override
    @Transactional
    public List<NeuralNetworkEntity> findAll() {
        return repository.findAll();
    }
}
