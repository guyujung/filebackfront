package com.example.upload.dto.response;

import com.example.upload.domain.Boards;
import com.example.upload.domain.Feedbacks;
import lombok.Builder;
import lombok.Data;

@Data
public class FeedbackStatusResponse {
    private Integer finishedCnt;

    private  Integer todoCnt;

    @Builder
    public FeedbackStatusResponse (Integer finishedCnt, Integer todoCnt){
        this.finishedCnt=finishedCnt;
        this.todoCnt=todoCnt;
    }

}
