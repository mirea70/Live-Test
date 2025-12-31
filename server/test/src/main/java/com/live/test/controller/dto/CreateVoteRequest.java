package com.live.test.controller.dto;

import com.live.test.domain.VoteChoice;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateVoteRequest {
    @NotNull(message = "choice must be one of [JJAJANG, JJAMPPONG]")
    private VoteChoice choice;
}
