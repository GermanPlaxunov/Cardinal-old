package org.project.market.database.service.interfaces;

import org.project.market.database.entity.AccountEntity;

public interface AccountService {

    AccountEntity findById(String id);

    void updateAccount(String id, Double change, Integer openPositions);

    Boolean isAnyOpenPosition(String accountId);
}
