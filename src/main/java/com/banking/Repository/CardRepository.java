package com.banking.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banking.entity.Card;

@Repository
public interface CardRepository extends JpaRepository<Card,Long> {

	Card save(Card card);

	List<Card> findAll();

	Optional<Card> findById(Long id);

	void deleteById(Long id);

}
