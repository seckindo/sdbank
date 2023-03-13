package org.perscholas.sdbank.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Cards {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @NonNull
    long cardNum;

    @NonNull
    double balance;


    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Cardtxns> cardtxnses = new LinkedHashSet<>();


    @ToString.Exclude
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "customer_id")
    Customers customer;

    public void addCardCustomer(Customers c) {
        this.setCustomer(c);
    }

    public void removeCustomer(Customers c) {
        this.setCustomer(null);
    }

}
