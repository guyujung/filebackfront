package com.example.upload.Service;

import com.example.upload.Repository.BoardRepository;
import com.example.upload.Repository.FeedbackStatusRepository;
import com.example.upload.Repository.MemberRepository;
import com.example.upload.Repository.TeamRepository;
import com.example.upload.domain.Boards;
import com.example.upload.domain.FeedbackStatuses;
import com.example.upload.domain.Members;
import com.example.upload.domain.Teams;
import com.example.upload.dto.response.BoardResponse;
import com.example.upload.dto.response.FeedbackStatusResponse;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class FeedbackStatusService {

    private FeedbackStatusRepository feedbackStatusRepository;
    private MemberRepository memberRepository;
    private TeamRepository teamRepository;
    private BoardRepository boardRepository;

    public FeedbackStatusResponse countFeedbacksForMemberAndTeam(Long memberId, Long teamId) {
        // memberId를 사용하여 해당 멤버의 알람 리스트를 가져옴
        Members member = memberRepository.findById(memberId).get();
        Teams teams = teamRepository.findById(teamId).get();

        List<Boards> boardsList = boardRepository.findByTeams(teams);
        List<BoardResponse> boardResponses = new ArrayList<>();
        Integer finishedCnt = 0; // 피드백을 한 경우 카운트
        Integer todoCnt = 0; // 피드백을 안 한 경우 카운트

        for (Boards board : boardsList) {
            FeedbackStatuses feedbackStatus = feedbackStatusRepository.findByBoardsAndUsers(board, member);

            BoardResponse boardResponse = BoardResponse.from(board);

            if (board.getUsers().getId().equals(memberId)) {
                boardResponse.setFeedbackYn(true);
            } else {
                boardResponse.setFeedbackYn(feedbackStatus.isFeedbackYn());
                if (feedbackStatus.isFeedbackYn()) {
                    finishedCnt++;
                } else {
                    todoCnt++;
                }
            }

        }
        return FeedbackStatusResponse.builder()
                .finishedCnt(finishedCnt)
                .todoCnt(todoCnt)
                .build();
    }
}








