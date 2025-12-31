package com.live.test.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface VoteRepository extends JpaRepository<Vote, UUID> {

    // For counting votes by choice
    long countByChoice(VoteChoice choice);

    // If we want to get all stats in one query (optional, but efficient)
    @Query("SELECT v.choice, COUNT(v) FROM Vote v GROUP BY v.choice")
    List<Object[]> countTotalVotesByChoice();
}
