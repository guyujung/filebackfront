package com.example.upload.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name="Team_members")
@Data
@EqualsAndHashCode(callSuper = false)
public class TeamMembers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_member_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Teams teams;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Members users;


    //연관관계 편의 메소드
    public void confirmTeam(Teams teams){
        this.teams=teams;
        teams.addTeams(this);
    }

    //연관관계 편의 메소드
    public void confirmMember(Members members){
        this.users=members;
        users.addMembers(this);
    }


}