package com.live.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.live.test.controller.dto.CreateVoteRequest;
import com.live.test.domain.Vote;
import com.live.test.domain.VoteChoice;
import com.live.test.service.VoteService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VoteController.class)
class VoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private VoteService voteService;

    @Test
    @DisplayName("투표 성공 테스트")
    void createVote() throws Exception {
        // given
        Vote vote = Vote.create(VoteChoice.JJAJANG);
        when(voteService.createVote(any())).thenReturn(vote);

        // when & then
        mockMvc.perform(post("/api/v1/votes")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"choice\": \"JJAJANG\"}"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.voteId").isNotEmpty())
                .andExpect(jsonPath("$.choice").value("JJAJANG"));
    }

    @Test
    @DisplayName("잘못된 선택지로 투표 시 실패 테스트")
    void createVote_invalidChoice() throws Exception {
        // when & then
        mockMvc.perform(post("/api/v1/votes")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"choice\": \"PIZZA\"}"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("INVALID_INPUT")) // MessageConversionException -> INVALID_INPUT
        // Or if it was enum mapping error, it might be different.
        // With Spring Boot and Enum, invalid value usually causes
        // HttpMessageNotReadableException
        // which our handler maps to INVALID_INPUT
        ;
    }

    @Test
    @DisplayName("결과 조회 테스트")
    void getResult() throws Exception {
        // given
        Map<String, Object> serviceResult = new HashMap<>();
        serviceResult.put("total", 10L);
        Map<String, Long> counts = new HashMap<>();
        counts.put("JJAJANG", 7L);
        counts.put("JJAMPPONG", 3L);
        serviceResult.put("counts", counts);

        when(voteService.getVoteResult()).thenReturn(serviceResult);

        // when & then
        mockMvc.perform(get("/api/v1/votes/result"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.total").value(10))
                .andExpect(jsonPath("$.counts.JJAJANG").value(7))
                .andExpect(jsonPath("$.counts.JJAMPPONG").value(3));
    }
}
