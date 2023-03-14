package org.perscholas.sdbank.service;

import jakarta.transaction.Transactional;
import lombok.ToString;
import org.perscholas.sdbank.dao.AccountsRepoI;
import org.perscholas.sdbank.dao.AccounttxnsRepoI;
import org.perscholas.sdbank.dao.CustomersRepoI;
import org.perscholas.sdbank.models.Accounts;
import org.perscholas.sdbank.models.Accounttxns;
import org.perscholas.sdbank.models.Customers;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional(rollbackOn = Exception.class)
public class AccountService {

    AccountsRepoI accountsRepoI;
    private final AccounttxnsRepoI accounttxnsRepoI;
    private final CustomersRepoI customersRepoI;

    public AccountService(AccountsRepoI accountsRepoI,
                          AccounttxnsRepoI accounttxnsRepoI,
                          CustomersRepoI customersRepoI) {
        this.accountsRepoI = accountsRepoI;
        this.accounttxnsRepoI = accounttxnsRepoI;
        this.customersRepoI = customersRepoI;
    }

    public void deleteAccount(int id) throws Exception {
        Optional<Accounts> toBeDeleted = accountsRepoI.findById(id);
        if(toBeDeleted.isPresent())
            accountsRepoI.delete(toBeDeleted.get());
        else
            throw new Exception("The Account cannot be find" + toBeDeleted);
    }

    public Accounts createOrUpdate(Accounts accounts) {
        if (accountsRepoI.findById(accounts.getId()).isPresent()) {
            Accounts original = accountsRepoI.findById(accounts.getId()).get();
            original.setRouting(accounts.getRouting());
            original.setAccountNum(accounts.getAccountNum());
            original.setBalance(accounts.getBalance());
            original.setCustomer(customersRepoI.findById(accounts.getCustomer().getId()).get());
            return accountsRepoI.save(original);
        } else {
            Accounts newacc = new Accounts();
            newacc.setRouting(accounts.getRouting());
            newacc.setAccountNum(accounts.getAccountNum());
            newacc.setBalance(accounts.getBalance());
            newacc.setCustomer(accounts.getCustomer());
            return accountsRepoI.save(newacc);
        }
    }

    public void depositMoney(Accounts accounts, double amount, String date){
        accounts.setBalance(accounts.getBalance() + amount);
        accountsRepoI.saveAndFlush(accounts);
        Accounttxns acctx = new Accounttxns(date,amount);
        accounttxnsRepoI.save(acctx);
    }

    public void withdrawMoney(Accounts accounts, double amount, String date){
        accounts.setBalance(accounts.getBalance() - amount);
        accountsRepoI.saveAndFlush(accounts);
        Accounttxns acctx = new Accounttxns(date,amount * -1);
        accounttxnsRepoI.save(acctx);
    }
}
