package org.project.data.repositories;

import org.project.data.entities.PriceDiffSignalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceDiffSignalRepository extends JpaRepository<PriceDiffSignalEntity, Long> {

    Long countBySymbol(String symbol);

    @Query("select signal from PriceDiffSignalEntity signal " +
            "where signal.symbol = :symbol and signal.date < :date " +
            "order by signal.date desc")
    List<PriceDiffSignalEntity> findPrevSignal(@Param("symbol") String symbol,
                                               @Param("date") LocalDateTime date);
}
