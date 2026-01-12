package com.banking.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.banking.entity.Card;
import com.banking.service.CardService;

@RestController
@RequestMapping("/cards")
public class CardController {

    @Autowired
    private CardService cardService;

    // CREATE with conditions
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Card card) {
        try {
            if (card.getCardNumber() == null || card.getCardNumber().isBlank()) {
                return new ResponseEntity<>("Card number is required", HttpStatus.BAD_REQUEST);
            }
            if (card.getCardType() == null || card.getCardType().isBlank()) {
                return new ResponseEntity<>("Card type is required", HttpStatus.BAD_REQUEST);
            }
            if (card.getExpiryDate() == null) {
                return new ResponseEntity<>("Expiry date is required", HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>(cardService.create(card), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            List<Card> cards = cardService.getAll();
            return new ResponseEntity<>(cards, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // READ BY ID with condition
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            if (id <= 0) {
                return new ResponseEntity<>("Invalid card id", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(cardService.getById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // UPDATE with conditions
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Card card) {
        try {
            if (id <= 0) {
                return new ResponseEntity<>("Invalid card id", HttpStatus.BAD_REQUEST);
            }
            if (card.getCardNumber() != null && card.getCardNumber().isBlank()) {
                return new ResponseEntity<>("Card number cannot be empty", HttpStatus.BAD_REQUEST);
            }
            if (card.getCardType() != null && card.getCardType().isBlank()) {
                return new ResponseEntity<>("Card type cannot be empty", HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>(cardService.update(id, card), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // DELETE with condition
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            if (id <= 0) {
                return new ResponseEntity<>("Invalid card id", HttpStatus.BAD_REQUEST);
            }
            cardService.delete(id);
            return new ResponseEntity<>("Card deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
