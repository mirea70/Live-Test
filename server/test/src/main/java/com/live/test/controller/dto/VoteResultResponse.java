package com.live.test.controller.dto;

import com.live.test.domain.VoteChoice;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.Map;

@Getter
@AllArgsConstructor
public class VoteResultResponse {
    private long total;
    private Map<String, Long> counts;
    private ZonedDateTime updatedAt;

    public static VoteResultResponse from(Map<String, Object> serviceResult) {
        return new VoteResultResponse(
                (Long) serviceResult.get("total"),
                (Map<String, Long>) serviceResult.get("counts"),
                ZonedDateTime.now());
    }
}
