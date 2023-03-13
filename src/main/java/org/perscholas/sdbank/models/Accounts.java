package org.perscholas.sdbank.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Accounts {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @NonNull
    int routing;

    @NonNull
    int accountNum;

    @NonNull
    double balance;


    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Accounttxns> accounttxn = new LinkedHashSet<>();



    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "customer_id")
    Customers customer;

    public void addAccountTxn(Accounttxns newAccountTxn) {
        accounttxn.add(newAccountTxn);
        newAccountTxn.setAccount(this);
    }

    public void removeAccountTxn(Accounttxns newAccountTxn) {
        accounttxn.remove(newAccountTxn);
        newAccountTxn.setAccount(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Accounts accounts = (Accounts) o;
        return id == accounts.id && accountNum == accounts.accountNum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountNum);
    }

    @Override
    public String toString() {
        return String.format("# %d | $ %.2f %n",accountNum,balance);
    }


}
