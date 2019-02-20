package com.hcy308.transaction.repository;

import com.hcy308.transaction.model.MagicLamp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LampRepository extends JpaRepository<MagicLamp, Long> {
}
