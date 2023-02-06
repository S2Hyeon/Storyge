package com.example.project.follow.model.entity;

import com.example.project.user.model.entity.User;
import lombok.*;

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

    @ManyToOne
    @JoinColumn(name="following")
    private User following;

    @ManyToOne
    @JoinColumn(name="follower")
    private User follower;


}
