package org.project.data.services.interfaces;

import org.project.data.entities.AccountEntity;

public interface AccountService {

    AccountEntity findById(String id);

    void updateAccount(String accountId, Double change, Integer openPositions);

    void updateAccount(AccountEntity account, Double income, Double commission, Integer openPositions);

    Boolean isAnyOpenPosition(String accountId);

    AccountEntity findActiveAccount();
}
