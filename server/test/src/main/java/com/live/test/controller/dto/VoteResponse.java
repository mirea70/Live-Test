package com.live.test.controller.dto;

import com.live.test.domain.Vote;
import com.live.test.domain.VoteChoice;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class VoteResponse {
    private UUID voteId;
    private VoteChoice choice;
    private LocalDateTime createdAt;

    public static VoteResponse from(Vote vote) {
        return new VoteResponse(vote.getId(), vote.getChoice(), vote.getCreatedAt());
    }
}
