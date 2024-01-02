package org.cardinal.data.repositories;

import org.cardinal.data.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    Optional<AccountEntity> findByAccountId(String accountId);

    List<AccountEntity> findAllByIsActive(Boolean isActive);
}
