package com.banking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.Repository.CardRepository;
import com.banking.entity.Card;

@Service
public class CardService {


	@Autowired
    private CardRepository cardRepository;


    // CREATE
    public Card create(Card card) {
        try {
            return cardRepository.save(card);
        } catch (Exception e) {
            throw new RuntimeException("Error while creating card", e);
        }
    }

    // READ ALL
    public List<Card> getAll() {
        try {
            return cardRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error while fetching cards", e);
        }
    }

    // READ BY ID
    public Card getById(Long id) {
        try {
            return ( cardRepository.findById(id))
                    .orElseThrow(() -> new RuntimeException("Card not found with id: " + id));
        } catch (Exception e) {
            throw new RuntimeException("Error while fetching card", e);
        }
    }

    // UPDATE
    public Card update(Long id, Card card) {
        try {
            Card existing = getById(id);

            existing.setCardNumber(card.getCardNumber());
            existing.setCardType(card.getCardType());
            existing.setExpiryDate(card.getExpiryDate());
            existing.setCvv(card.getCvv());
            existing.setStatus(card.getStatus());
            existing.setAccount(card.getAccount());

            return cardRepository.save(existing);
        } catch (Exception e) {
            throw new RuntimeException("Error while updating card", e);
        }
    }

    // DELETE
    public void delete(Long id) {
        try {
            cardRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error while deleting card", e);
        }
    }


}
