package org.project.data.services.interfaces;

import org.project.data.entities.AccountEntity;

public interface AccountService {

    AccountEntity findById(String id);

    void updateAccount(String id, Double change, Integer openPositions);

    Boolean isAnyOpenPosition(String accountId);
}
