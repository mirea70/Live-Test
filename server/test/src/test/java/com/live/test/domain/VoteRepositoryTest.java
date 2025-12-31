package com.live.test.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class VoteRepositoryTest {

    @Autowired
    private VoteRepository voteRepository;

    @Test
    @DisplayName("투표 저장 및 조회 테스트")
    void saveAndFindVote() {
        // given
        Vote vote = Vote.create(VoteChoice.JJAJANG);

        // when
        Vote savedVote = voteRepository.save(vote);

        // then
        assertThat(savedVote.getId()).isNotNull();
        assertThat(savedVote.getChoice()).isEqualTo(VoteChoice.JJAJANG);
        assertThat(savedVote.getCreatedAt()).isNotNull();
    }

    @Test
    @DisplayName("투표 결과 집계 테스트")
    void countVotes() {
        // given
        voteRepository.save(Vote.create(VoteChoice.JJAJANG));
        voteRepository.save(Vote.create(VoteChoice.JJAJANG));
        voteRepository.save(Vote.create(VoteChoice.JJAMPPONG));

        // when
        long jjajangCount = voteRepository.countByChoice(VoteChoice.JJAJANG);
        long jjamppongCount = voteRepository.countByChoice(VoteChoice.JJAMPPONG);

        // then
        assertThat(jjajangCount).isEqualTo(2);
        assertThat(jjamppongCount).isEqualTo(1);
    }

    @Test
    @DisplayName("GROUP BY 쿼리 테스트")
    void countTotalVotesByChoice() {
        // given
        voteRepository.save(Vote.create(VoteChoice.JJAJANG));
        voteRepository.save(Vote.create(VoteChoice.JJAJANG));
        voteRepository.save(Vote.create(VoteChoice.JJAMPPONG));

        // when
        List<Object[]> results = voteRepository.countTotalVotesByChoice();

        // then
        assertThat(results).hasSize(2);
        // results order depends on DB implementation, so checking content
        // usually List<[VoteChoice, Long]>
    }
}
