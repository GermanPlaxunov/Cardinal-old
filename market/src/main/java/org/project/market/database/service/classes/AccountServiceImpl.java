package org.project.market.database.service.classes;

import lombok.RequiredArgsConstructor;
import org.project.market.database.entity.AccountEntity;
import org.project.market.database.repository.AccountRepository;
import org.project.market.database.service.interfaces.AccountService;

@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public AccountEntity findById(String id) {
        return accountRepository.findByAccountId(id)
                .orElse(null);
    }

    @Override
    public void updateBalance(String id, Double change) {
        accountRepository.updateAccountEntitiesByAccountId(change, id);
    }
}
