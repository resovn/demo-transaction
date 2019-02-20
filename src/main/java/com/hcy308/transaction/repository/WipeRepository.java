package com.hcy308.transaction.repository;

import com.hcy308.transaction.model.Wipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WipeRepository extends JpaRepository<Wipe, Long> {

    List<Wipe> findAllByLampId(String lampId);

    List<Wipe> findAllByWiper(String wiper);

}
