package org.perscholas.sdbank.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Customers {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @NonNull
    String firstName;

    @NonNull
    String lastName;

    @NonNull
    String email;

    @NonNull
    String addressStreet;

    @NonNull
    String city;

    @NonNull
    String state;

    @NonNull
    int zip;

    @NonNull
    String dob;

    @NonNull
    String pob;

    @NonNull
    String ssn;


    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Cards> card = new LinkedHashSet<>();


    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Accounts> account = new LinkedHashSet<>();

    public void addAccount(Accounts newAccount) {
        account.add(newAccount);
        newAccount.setCustomer(this);
    }

    public void removeAccount(Accounts newAccount) {
        account.remove(newAccount);
        newAccount.setCustomer(null);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customers customers = (Customers) o;
        return Objects.equals(id, customers.id) && firstName.equals(customers.firstName) && lastName.equals(customers.lastName) && ssn.equals(customers.ssn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, ssn);
    }
}
