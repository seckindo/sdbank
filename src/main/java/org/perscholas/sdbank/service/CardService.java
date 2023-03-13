package org.perscholas.sdbank.service;

import jakarta.transaction.Transactional;
import org.perscholas.sdbank.dao.CardRepoI;
import org.perscholas.sdbank.models.Accounts;
import org.perscholas.sdbank.models.Cards;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional(rollbackOn = Exception.class)
public class CardService {

    CardRepoI cardRepoI;

    public CardService(CardRepoI cardRepoI) {
        this.cardRepoI = cardRepoI;
    }

    public void deleteCard(int id) throws Exception {
        Optional<Cards> toBeDeleted = cardRepoI.findById(id);
        if(toBeDeleted.isPresent())
            cardRepoI.delete(toBeDeleted.get());
        else
            throw new Exception("The Credit Card cannot be find" + toBeDeleted);
    }

    public Cards createOrUpdate(Cards cards) {
        if (cardRepoI.findByCardNum(cards.getCardNum()).isPresent()) {
            Cards original = cardRepoI.findByCardNum(cards.getCardNum()).get();
            original.setCardNum(cards.getCardNum());
            original.setBalance(cards.getBalance());
            return cardRepoI.save(original);
        } else {
            return cardRepoI.save(cards);
        }
    }
}
