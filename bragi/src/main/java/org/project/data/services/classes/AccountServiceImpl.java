package org.project.data.services.classes;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.data.entities.AccountEntity;
import org.project.data.repositories.AccountRepository;
import org.project.data.services.interfaces.AccountService;

import java.util.Objects;

@Slf4j
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
    public void updateAccount(String accountId, Double change, Integer openPositions) {
        var account = accountRepository.findByAccountId(accountId)
                .orElse(null);
        if (account != null) {
            var newBalance = account.getBalance() + change;
            account.setBalance(newBalance)
                    .setOpenPositionCount(openPositions);
            accountRepository.save(account);
        } else {
            log.error("No account found by accountId: {}", accountId);
        }
    }

    @Override
    public void updateAccount(AccountEntity account, Double income, Double commission, Integer openPositions) {
        var balance = account.getBalance();
        account.setBalance(balance + income - commission)
                .setOpenPositionCount(openPositions);
        accountRepository.saveAndFlush(account);
    }

    @Override
    public Boolean isAnyOpenPosition(String accountId) {
        return accountRepository.findByAccountId(accountId)
                .orElse(null)
                .getOpenPositionCount() > 0;
    }

    @Override
    public AccountEntity findActiveAccount() {
        return accountRepository.findAllByIsActive(Boolean.TRUE)
                .stream()
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
    }
}
