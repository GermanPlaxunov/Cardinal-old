package org.project.market.database.repository;

import org.project.market.database.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    Optional<AccountEntity> findByAccountId(String accountId);

    @Modifying
    @Query("update AccountEntity acc set acc.balance = acc.balance + :change " +
            "where acc.accountId = :accountId")
    void updateAccountEntitiesByAccountId(@Param("change") Double change,
                                          @Param("accountId") String accountId);

}
