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
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "follow_id")
    private Long followId;

    @Column(name = "following")
    private Long following;
    @ManyToOne
    @JoinColumn(name = "following", insertable = false, updatable = false)
    private User followingUsers;

    @Column(name = "follower")
    private Long follower;
    @ManyToOne
    @JoinColumn(name = "follower", updatable = false, insertable = false)
    private User followerUsers;


}
