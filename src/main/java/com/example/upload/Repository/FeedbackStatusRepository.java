package com.example.upload.Repository;

import com.example.upload.domain.Boards;
import com.example.upload.domain.FeedbackStatuses;
import com.example.upload.domain.Members;
import com.example.upload.domain.Teams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackStatusRepository extends JpaRepository<FeedbackStatuses, Long> {



    FeedbackStatuses findByBoardsAndUsers(Boards boards, Members users);

    List<FeedbackStatuses> findByBoards(Boards board);

    List<FeedbackStatuses>  findByTeams(Teams teams);

    List<FeedbackStatuses> findByUsersAndTeams(Members member, Teams team);

    List<FeedbackStatuses> findByUsers(Members member);
}
