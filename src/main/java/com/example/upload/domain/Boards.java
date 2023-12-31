package com.example.upload.domain;

import com.example.upload.BaseTimeEntity;
import com.example.upload.Service.AlarmService;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name="Boards")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class Boards extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Teams teams;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_id")
    private Works works;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Members users;

    @Column(nullable = false, length = 20)
    private String title;

    @Column(length = 3000)
    private String content;



    @Column(name = "view_cnt")
    private Long viewCnt;     //조회수

    @Column(name = "del_yn")
    private boolean delYn;

    @Column(name = "feedback_yn")
    private boolean feedbackYn;

    //== 게시글을 삭제하면 달려있는 댓글 모두 삭제 ==//
    @OneToMany(mappedBy = "boards", cascade = ALL, orphanRemoval = true)
    private List<Feedbacks> feedbackList = new ArrayList<>();

    //== 게시글을 삭제하면 달려있는 댓글 모두 삭제 ==//
    @OneToMany(mappedBy = "boards", cascade = ALL, orphanRemoval = true)
    private List<FeedbackStatuses> feedbackStatusList = new ArrayList<>();

    @OneToMany(mappedBy = "boards", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Files> fileList = new ArrayList<>();

    public Boards updateViewCount(Long viewCount){
        this.viewCnt = viewCount+1;
        return this;
    }

    //피드백 추가,연관관계 편의 메소드
    public void addFeedbacks(Feedbacks feedbacks){
        //comment의 Post 설정은 comment에서 함
        feedbackList.add(feedbacks);
    }

    //파일 추가,연관관계 편의 메소드
    public void addFiles(Files files){
        //comment의 Post 설정은 comment에서 함
        fileList.add(files);
    }

    public void addFeedbackStatuses(FeedbackStatuses feedbackStatuses){
        //comment의 Post 설정은 comment에서 함
        feedbackStatusList.add(feedbackStatuses);
    }
    //연관관계 편의 메소드
    public void confirmMember(Members members){
        this.users=members;
        users.addBoards(this);
    }

    //연관관계 편의 메소드
    public void confirmTeam(Teams teams){
        this.teams=teams;
        teams.addBoards(this);
    }

    //연관관계 편의 메소드
    public void confirmWork(Works works){
        this.works=works;
        works.addBoards(this);
    }
    @Builder
    public Boards(String title, String content){
        this.title = title;
        this.content = content;
        this.viewCnt = 0L;
        this.delYn=false;
        this.feedbackYn=false;
    }



}
