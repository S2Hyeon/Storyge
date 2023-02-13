package com.example.project.follow.model.entity;

import com.example.project.user.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name="follow_waiting")
public class FollowWait {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "waiting_id")
    private Long waitingId;


    @Column(name = "following")
    private Long following;

    @ManyToOne
    @JoinColumn(name="following", insertable = false,updatable = false)
    private User followWaitUser;

    @Column(name = "user_id")
    private Long userId;


    @ManyToOne
    @JoinColumn(name="user_id", insertable = false,updatable = false)
    private User user;

}
