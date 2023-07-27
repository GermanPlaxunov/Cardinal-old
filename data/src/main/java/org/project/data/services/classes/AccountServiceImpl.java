package org.project.data.services.classes;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.project.data.entities.AccountEntity;
import org.project.data.repositories.AccountRepository;
import org.project.data.services.interfaces.AccountService;

@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    @Transactional
    public AccountEntity findById(String id) {
        return accountRepository.findByAccountId(id)
                .orElse(null);
    }

    @Override
    @Transactional
    public void updateAccount(String id, Double change, Integer openPositions) {
        accountRepository.updateAccountEntitiesByAccountId(change, id, openPositions);
    }

    @Override
    public Boolean isAnyOpenPosition(String accountId) {
        return accountRepository.findByAccountId(accountId)
                .orElse(null)
                .getOpenPositionCount() > 0;
    }
}
