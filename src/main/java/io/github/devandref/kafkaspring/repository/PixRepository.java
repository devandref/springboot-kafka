package io.github.devandref.kafkaspring.repository;

import io.github.devandref.kafkaspring.model.Pix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PixRepository extends JpaRepository<Pix, Integer> {
}
