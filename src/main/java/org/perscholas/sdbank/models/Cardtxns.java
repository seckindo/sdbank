package org.perscholas.sdbank.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Entity
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Cardtxns {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @NonNull
    String date;

    @NonNull
    double amount;


    @ToString.Exclude
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "card_id")
    Cards card;

    public void addCard(Cards c) {
        this.setCard(c);
    }

    public void removeCard(Cards c) {
        this.setCard(null);
    }

}
