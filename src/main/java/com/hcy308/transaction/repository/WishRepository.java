package com.hcy308.transaction.repository;

import com.hcy308.transaction.model.Wish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishRepository extends JpaRepository<Wish, Long> {

    List<Wish> findAllByWipeId(long wipeId);

}
