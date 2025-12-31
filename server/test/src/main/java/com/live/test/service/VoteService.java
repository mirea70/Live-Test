package com.live.test.service;

import com.live.test.domain.Vote;
import com.live.test.domain.VoteChoice;
import com.live.test.domain.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VoteService {

    private final VoteRepository voteRepository;

    @Transactional
    public Vote createVote(VoteChoice choice) {
        Vote vote = Vote.create(choice);
        return voteRepository.save(vote);
    }

    public Map<String, Object> getVoteResult() {
        // Optimized query: SELECT choice, COUNT(*) ... GROUP BY choice
        // But for simplicity/readability vs consistency, we can use the efficient query
        // method.
        var results = voteRepository.countTotalVotesByChoice();

        long jjajangCount = 0;
        long jjamppongCount = 0;

        for (Object[] result : results) {
            VoteChoice choice = (VoteChoice) result[0];
            Long count = (Long) result[1];
            if (choice == VoteChoice.JJAJANG) {
                jjajangCount = count;
            } else if (choice == VoteChoice.JJAMPPONG) {
                jjamppongCount = count;
            }
        }

        long total = jjajangCount + jjamppongCount;

        // Constructing a map as a simple result object.
        // In a strictly layered architecture, we might return a dedicated DTO.
        Map<String, Object> response = new HashMap<>();
        response.put("total", total);

        Map<String, Long> counts = new HashMap<>();
        counts.put(VoteChoice.JJAJANG.name(), jjajangCount);
        counts.put(VoteChoice.JJAMPPONG.name(), jjamppongCount);

        response.put("counts", counts);

        return response;
    }
}
