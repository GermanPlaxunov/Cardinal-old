package org.project.core.database.repository;

import org.project.core.database.entity.PriceDiffSignalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PriceDiffSignalRepository extends JpaRepository<PriceDiffSignalEntity, Long> {

    Long countBySymbol(String symbol);

    @Query("select signal from PriceDiffSignalEntity signal " +
            "where signal.symbol = :symbol and signal.date < :date " +
            "order by signal.date desc")
    List<PriceDiffSignalEntity> findPrevSignal(@Param("symbol") String symbol,
                                               @Param("date") LocalDateTime date);
}
