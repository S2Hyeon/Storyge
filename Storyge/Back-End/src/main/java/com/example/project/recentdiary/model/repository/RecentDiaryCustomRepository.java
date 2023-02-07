package com.example.project.recentdiary.model.repository;

import com.example.project.recentdiary.model.entity.RecentDiary;
import com.example.project.user.model.entity.User;

import java.util.List;

public interface RecentDiaryCustomRepository {

//    @Query("select r from RecentDiary r fetch r.user_id u"
//    +"where u in "
//    +"(select f from follow where f.follower = :user)")
    List<RecentDiary> selectAllRecentDiaryByFollowing(User userId);
}
