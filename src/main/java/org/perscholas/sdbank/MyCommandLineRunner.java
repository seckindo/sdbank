package org.perscholas.sdbank;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.perscholas.sdbank.dao.*;
import org.perscholas.sdbank.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MyCommandLineRunner implements CommandLineRunner {

     CustomersRepoI customersRepoI;


     AccountsRepoI accountsRepoI;

    AccounttxnsRepoI accounttxnsRepoI;

    CardRepoI cardRepoI;

    CardtxnsRepoI cardtxnsRepoI;

    MyUserRepoI myUserRepoI;

    AuthGroupRepoI authGroupRepoI;

    @Autowired
    public MyCommandLineRunner(CustomersRepoI customersRepoI, AccountsRepoI accountsRepoI, AccounttxnsRepoI accounttxnsRepoI, CardRepoI cardRepoI, CardtxnsRepoI cardtxnsRepoI, MyUserRepoI myUserRepoI, AuthGroupRepoI authGroupRepoI) {
        this.customersRepoI = customersRepoI;
        this.accountsRepoI = accountsRepoI;
        this.accounttxnsRepoI = accounttxnsRepoI;
        this.cardRepoI = cardRepoI;
        this.cardtxnsRepoI = cardtxnsRepoI;
        this.myUserRepoI = myUserRepoI;
        this.authGroupRepoI = authGroupRepoI;
    }

    @Override
    public void run(String... args) throws Exception {
        Accounts accounts = new Accounts(1000,1000,0);
        Accounts accounts2 = new Accounts(2000,2000,2.22);
        Accounts accounts3 = new Accounts(3000,3000,3.3);

        Customers customers = new Customers("Test","Lasttest","123@aaa.com","1101 Space Rd","Dallas","TX",75200,"01/01/1950","Dallas","111-11-1111");
        Customers customers2 = new Customers("Test2","Lasttest2","234@aaa.com","1101 Mars Rd","Dallas","TX",75200,"01/01/1950","Dallas","222-22-2222");
        Customers customers3 = new Customers("Test3","Lasttest3","456@aaa.com","1101 Venus Rd","Dallas","TX",75200,"01/01/1950","Dallas","333-33-3333");

        Accounttxns accounttxns = new Accounttxns("02/25/2023",1000.0);

        customers.addAccount(accounts);
        customers2.addAccount(accounts2);
        customers3.addAccount(accounts3);

        accounts.addAccountTxn(accounttxns);


        customersRepoI.save(customers);
        customersRepoI.save(customers2);
        customersRepoI.save(customers3);



        accounttxnsRepoI.save(accounttxns);

        myUserRepoI.save(new MyUser("seckin@gmail.com","$2a$04$Er.ub.ZWpclPqUG6ERF1Au25CGY8xh1Q.JdiY819mF/f3lotQuc2K"));
        myUserRepoI.save(new MyUser("user@gmail.com","$2a$04$Er.ub.ZWpclPqUG6ERF1Au25CGY8xh1Q.JdiY819mF/f3lotQuc2K"));

        authGroupRepoI.save(new AuthGroup("seckin@gmail.com","ROLE_ADMIN"));
        authGroupRepoI.save(new AuthGroup("seckin@gmail.com","ROLE_USER"));
        authGroupRepoI.save(new AuthGroup("user@gmail.com","ROLE_USER"));





//        accountsRepoI.saveAndFlush(accounts);
//        accountsRepoI.saveAndFlush(accounts2);
//        accountsRepoI.saveAndFlush(accounts3);

//

//        accounttxnsRepoI.saveAndFlush(accounttxns);

 //       Cards cards = new Cards(444444444,5000.00);
 //       cardRepoI.saveAndFlush(cards);

 //       Cardtxns cardtxns = new Cardtxns("02/15/2023", 1000.00);
 //       cardtxnsRepoI.saveAndFlush(cardtxns);

//        accounttxns.addAccount(accounts);
//        accounttxnsRepoI.saveAndFlush(accounttxns);
//
//        cards.addCardCustomer(customers);
//        cardRepoI.saveAndFlush(cards);
//
//        cardtxns.addCard(cards);
//        cardtxnsRepoI.saveAndFlush(cardtxns);





    }
}
