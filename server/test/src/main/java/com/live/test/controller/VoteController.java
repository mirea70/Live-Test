package com.live.test.controller;

import com.live.test.controller.dto.CreateVoteRequest;
import com.live.test.controller.dto.VoteResponse;
import com.live.test.controller.dto.VoteResultResponse;
import com.live.test.domain.Vote;
import com.live.test.service.VoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/votes")
@RequiredArgsConstructor
public class VoteController {

    private final VoteService voteService;

    @PostMapping
    public ResponseEntity<VoteResponse> createVote(@RequestBody @Valid CreateVoteRequest request) {
        Vote vote = voteService.createVote(request.getChoice());
        return ResponseEntity.status(HttpStatus.CREATED).body(VoteResponse.from(vote));
    }

    @GetMapping("/result")
    public ResponseEntity<VoteResultResponse> getResult() {
        return ResponseEntity.ok(VoteResultResponse.from(voteService.getVoteResult()));
    }
}
