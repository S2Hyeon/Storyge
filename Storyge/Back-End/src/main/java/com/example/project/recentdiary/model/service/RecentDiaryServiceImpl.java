package com.example.project.recentdiary.model.service;

import com.example.project.diary.model.entity.Diary;
import com.example.project.follow.model.repository.FollowRepository;
import com.example.project.recentdiary.model.dto.RecentDiaryResponseDto;
import com.example.project.recentdiary.model.entity.ReadDiary;
import com.example.project.recentdiary.model.entity.RecentDiary;
import com.example.project.recentdiary.model.repository.ReadDiaryRepository;
import com.example.project.recentdiary.model.repository.RecentDiaryCustomRepository;
import com.example.project.recentdiary.model.repository.RecentDiaryRepository;
import com.example.project.user.model.entity.User;
import com.example.project.user.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RecentDiaryServiceImpl implements RecentDiaryService {


    private final RecentDiaryRepository recentDiaryRepository;
    private final ReadDiaryRepository readDiaryRepository;
    private final FollowRepository followRepository;
    private final UserRepository userRepository;
    private final RecentDiaryCustomRepository recentDiaryCustomRepository;


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
//        User user = null; // 현재 로그인한 사용자
        User user =userRepository.findById(3L).orElse(null);
        System.out.println(followRepository.findByFollower(user).size()+"!23123");
        if(followRepository.findByFollower(user).size()==0){
            return null;
        }
//        List<Follow> followList = followRepository.findByFollower(user);
//        List<RecentDiaryResponseDto> recentDiaryList = new ArrayList<>();
//        int i=0;
//        for(Follow follow:followList){
//            RecentDiary recentDiary = recentDiaryRepository.findByUserId(follow.getFollowing()).orElse(null);
//            if(recentDiary.getCreatedAt().plusHours(24).compareTo(LocalDateTime.now())>0){
//                recentDiaryRepository.delete(recentDiary);
//            }
//            else{
//                recendDiaryList.add(RecentDiaryResponseDto.builder()
//                        .diaryId(recentDiary.getDiaryId().getDiaryId())
//                        .nickname(recentDiary.getUserId().getNickname())
//                        .profileImg(recentDiary.getUserId().getProfileImg())
//                        .build());
//            }
//
//        }


        List<RecentDiary> recentDiaryList = recentDiaryCustomRepository.selectAllRecentDiaryByFollowing(user);
        List<RecentDiaryResponseDto> recentDiaryDtoList = new ArrayList<>();
        int i=0;
        for(RecentDiary recentDiary : recentDiaryList){
            RecentDiary recent = recentDiaryRepository.findById(recentDiary.getRecentId()).orElse(null);

            if(readDiaryRepository.findByUserIdAndAndRecentId(user,recent)==null){
                recentDiaryDtoList.add(RecentDiaryResponseDto.builder()
                        .diaryId(recentDiary.getDiaryId().getDiaryId())
                        .profileImg(recentDiary.getUserId().getProfileImg())
                        .nickname(recentDiary.getUserId().getNickname())
                        .build());
                i++;
            }
            if(i>=20){
                break;
            }

        }
        System.out.println(recentDiaryList.size()+"1111111111");
        System.out.println(recentDiaryDtoList.size());

        return recentDiaryDtoList;
    }
}
