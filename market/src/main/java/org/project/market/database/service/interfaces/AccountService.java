package org.project.market.database.service.interfaces;

import org.project.market.database.entity.AccountEntity;

public interface AccountService {

    AccountEntity findById(String id);

    void updateBalance(String id, Double change);
}
