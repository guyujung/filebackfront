package com.example.upload.dto.response;


import com.example.upload.Service.AlarmService;
import com.example.upload.domain.Boards;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class BoardResponse {



private Long boardId;
private String title;

private LocalDateTime createdTime;

private Long viewCount;

private String workName;

private String writerName;

private boolean feedbackYn;

private Integer finishedCnt; //완효 피드백 개수

private Integer todoCnt; //미완료 피드백 개수

private Long userId;
//사용자명과 작업명은 추후 추가해야함
//private String username;

//private String workname;
// 생성자
public BoardResponse(Integer finishedCnt, Integer todoCnt) {
    this.finishedCnt = finishedCnt;
    this.todoCnt = todoCnt;
}

    @Builder
    public BoardResponse(Long boardId, String title, Long viewCount,
                         LocalDateTime createdTime, String workName,String writerName,
                         boolean feedbackYn,Long userId){
        this.boardId = boardId;
        this.title = title;
        this.viewCount=viewCount;
        this.createdTime=createdTime;
        this.workName=workName;
        this.writerName=writerName;
        this.feedbackYn=feedbackYn;
        this.userId=userId;
    }

    public static BoardResponse from(Boards boards) {
        return BoardResponse.builder()
                .boardId(boards.getId())
                .title(boards.getTitle())
                .viewCount(boards.getViewCnt())
                .createdTime(boards.getCreatedAt())
                .workName(boards.getWorks().getWorkName())
                .writerName(boards.getUsers().getStudentNumber()+" "+boards.getUsers().getUserName())
                .userId(boards.getUsers().getId())
                .build();
    }
}
