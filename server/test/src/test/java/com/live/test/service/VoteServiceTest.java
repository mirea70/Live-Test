package com.live.test.service;

import com.live.test.domain.Vote;
import com.live.test.domain.VoteChoice;
import com.live.test.domain.VoteRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VoteServiceTest {

    @InjectMocks
    private VoteService voteService;

    @Mock
    private VoteRepository voteRepository;

    @Test
    @DisplayName("투표 생성 테스트")
    void createVote() {
        // given
        Vote vote = Vote.create(VoteChoice.JJAJANG);
        when(voteRepository.save(any(Vote.class))).thenReturn(vote);

        // when
        Vote savedVote = voteService.createVote(VoteChoice.JJAJANG);

        // then
        assertThat(savedVote.getChoice()).isEqualTo(VoteChoice.JJAJANG);
    }

    @Test
    @DisplayName("투표 결과 조회 테스트")
    void getVoteResult() {
        // given
        List<Object[]> queryResults = new ArrayList<>();
        queryResults.add(new Object[] { VoteChoice.JJAJANG, 10L });
        queryResults.add(new Object[] { VoteChoice.JJAMPPONG, 5L });

        when(voteRepository.countTotalVotesByChoice()).thenReturn(queryResults);

        // when
        Map<String, Object> result = voteService.getVoteResult();

        // then
        assertThat(result.get("total")).isEqualTo(15L);

        @SuppressWarnings("unchecked")
        Map<String, Long> counts = (Map<String, Long>) result.get("counts");

        assertThat(counts.get("JJAJANG")).isEqualTo(10L);
        assertThat(counts.get("JJAMPPONG")).isEqualTo(5L);
    }
}
