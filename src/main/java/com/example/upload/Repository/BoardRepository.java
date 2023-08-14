package com.example.upload.Repository;


import com.example.upload.domain.Boards;
import com.example.upload.domain.FeedbackStatuses;
import com.example.upload.domain.Members;
import com.example.upload.domain.Teams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Boards,Long>
{
    List<Boards> findByTeams(Teams team);
    Boards findByIdAndTeamsId(Long boardId, Long teamId);


}
