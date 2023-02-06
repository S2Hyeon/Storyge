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

    @ManyToOne
    @JoinColumn(name="following")
    private User following;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User userId;

}
