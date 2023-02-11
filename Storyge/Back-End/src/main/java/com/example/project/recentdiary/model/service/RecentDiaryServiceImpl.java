package com.example.project.recentdiary.model.service;

import com.example.project.diary.model.entity.Diary;
import com.example.project.diary.model.repository.DiaryRepository;
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
    private final UserRepository userRepository;
    private final RecentDiaryCustomRepository recentDiaryCustomRepository;
    private final DiaryRepository diaryRepository;


    @Override
    public void insertRecentDiary(Long user, Long diary) {

        User diaryUser = userRepository.findById(user).orElse(null);
        Diary insertDiary =diaryRepository.findById(diary).orElse(null);

        if(insertDiary.getScope()==1){ // 공개일때
            RecentDiary recent = recentDiaryRepository.findByUserId(diaryUser).orElse(null);
            if(recent!=null){
                 recentDiaryRepository.deleteByUserId(diaryUser); // 이미 24시간 내에 작성한 일기가 있다면 삭제
            }
            recentDiaryRepository.save(RecentDiary.builder()
                    .userId(diaryUser)
                    .diaryId(insertDiary)
                    .build());

        }
    }

    @Override
    public Boolean insertReadDiary(Long userId, Long diaryId) {

        User currentUser = userRepository.findById(userId).orElse(null); // 현재 user -> 변경해야 함
        Diary nowDiary = diaryRepository.findById(diaryId).orElse(null); // 현재 읽은 diary

//        User diaryUser = userRepository.findById(nowDiary.getDiaryId()).orElse(null); // 다이어리 작성한 userid
        RecentDiary diary = recentDiaryRepository.findByDiaryId(nowDiary).orElse(null); // recent diary에 있는지 확인
        if(diary==null ||(diary.getEndsAt().isBefore(LocalDateTime.now()))){ // recent diary에 존재하지 않거나 이미 24시간이 지난 diary
            return false;
        }
        else{
            if(diary.getUserId()!=currentUser){ // 현재 user와 글 쓴 user가 다름
                readDiaryRepository.save(ReadDiary.builder()
                        .userId(currentUser)
                        .recentId(diary)
                        .build());
            }
            return true;
        }



    }

    @Override
    public List<RecentDiaryResponseDto> selectAllRecentDiary(Long userId) {
//        User user = null; // 현재 로그인한 사용자
        User user =userRepository.findById(userId).orElse(null);
        if(followRepository.findAllByFollower(userId).size()==0){
            return null;
        }
        
        List<RecentDiary> recentDiaryList = recentDiaryCustomRepository.selectAllRecentDiaryByFollowing(user); // 팔로우 하는 사용자의 recentdiary 찾기
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

        return recentDiaryDtoList;
    }
}
