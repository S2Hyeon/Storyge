package com.example.project.recentdiary.model.service;

import com.example.project.diary.model.entity.Diary;
import com.example.project.follow.model.entity.Follow;
import com.example.project.follow.model.repository.FollowRepository;
import com.example.project.recentdiary.model.dto.RecentDiaryResponseDto;
import com.example.project.recentdiary.model.entity.ReadDiary;
import com.example.project.recentdiary.model.entity.RecentDiary;
import com.example.project.recentdiary.model.repository.ReadDiaryRepository;
import com.example.project.recentdiary.model.repository.RecentDiaryRepository;
import com.example.project.user.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RecentDiaryServiceImpl implements RecentDiaryService {


    private final RecentDiaryRepository recentDiaryRepository;
    private final ReadDiaryRepository readDiaryRepository;
    private final FollowRepository followRepository;


    @Override
    public void insertRecentDiary(User user, Diary diary) {
        if(diary.getScope()==1){ // 공개일때
            RecentDiary recent = recentDiaryRepository.findByUserId(user).orElse(null);
            if(recent!=null){
                 recentDiaryRepository.deleteByUserId(user); // 이미 24시간 내에 작성한 일기가 있다면 삭제
            }
            recentDiaryRepository.save(RecentDiary.builder()
                    .userId(user)
                    .diaryId(diary)
                    .build());

        }
    }

    @Override
    public void insertReadDiary(User user, RecentDiary recentDiary) {
        readDiaryRepository.save(ReadDiary.builder()
                .userId(user)
                .recentId(recentDiary)
                .build());
    }

    @Override
    public List<RecentDiaryResponseDto> selectAllRecentDiary() {
        User user = null; // 현재 로그인한 사용자
        List<Follow> followList = followRepository.findByFollower(user);
        List<RecentDiaryResponseDto> recendDiaryList = new ArrayList<>();
        int i=0;
        for(Follow follow:followList){
            RecentDiary recentDiary = recentDiaryRepository.findByUserId(follow.getFollowing()).orElse(null);
            if(recentDiary.getCreatedAt().plusHours(24).compareTo(LocalDateTime.now())>0){
                recentDiaryRepository.delete(recentDiary);
            }
            else{
                recendDiaryList.add(RecentDiaryResponseDto.builder()
                        .diaryId(recentDiary.getDiaryId().getDiaryId())
                        .nickname(recentDiary.getUserId().getNickname())
                        .profileImg(recentDiary.getUserId().getProfileImg())
                        .build());
            }

        }
        return null;
    }
}
