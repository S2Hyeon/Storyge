package com.example.project.follow.model.entity;

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

//    @ManyToOne
//    @JoinColumn(name="user_id")
//    private User following;
//
//    @ManyToOne
//    @JoinColumn(name="user_id")
//    private User follower;


}
