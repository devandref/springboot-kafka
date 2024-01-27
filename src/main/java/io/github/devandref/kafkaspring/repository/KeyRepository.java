package io.github.devandref.kafkaspring.repository;

import io.github.devandref.kafkaspring.model.Key;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KeyRepository extends JpaRepository<Key, Integer> {

    Optional<Key> findByChave(String chave);

}
