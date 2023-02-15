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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class RecentDiaryServiceImpl implements RecentDiaryService {


    private final RecentDiaryRepository recentDiaryRepository;
    private final ReadDiaryRepository readDiaryRepository;
    private final FollowRepository followRepository;
    private final RecentDiaryCustomRepository recentDiaryCustomRepository;
    private final DiaryRepository diaryRepository;


    // recentdiary에 insert
    @Override
    public void insertRecentDiary(Long user, Long diary) {
        // user: 일기 쓴 사람, diary: 쓴 다이어리 id

        Diary insertDiary = diaryRepository.findById(diary).orElseThrow();

        if (insertDiary.getScope() == 1) { // 공개일때
            Optional<RecentDiary> recent = recentDiaryRepository.findByUserId(user);
            if (recent.isPresent()) {
                recentDiaryRepository.deleteByUserId(user); // 이미 24시간 내에 작성한 일기가 있다면 삭제
            }
            recentDiaryRepository.save(RecentDiary.builder()
                    .userId(user)
                    .diaryId(diary)
                    .endsAt(insertDiary.getCreatedAt().plusHours(24))
                    .build());

        }
    }

    // 팔로잉의 일기를 읽는다
    @Override
    public Boolean insertReadDiary(Long userId, Long diaryId) {

        /*
            사용자가 일기를 읽는다
            이미 읽었는지 확인을 한다
            만약 읽지 않았다면 read diary에 추가
         */

        RecentDiary diary = recentDiaryRepository.findByDiaryId(diaryId).orElse(null); // recent diary에 있는지 확인

        if (diary == null || (diary.getEndsAt().isBefore(LocalDateTime.now()))) { // recent diary에 존재하지 않거나 이미 24시간이 지난 diary
            return false;
        } else {
            if (!Objects.equals(diary.getUserId(), userId)) { // 현재 user와 글 쓴 user가 다름
                Long recentDiaryId = diary.getRecentId();

                if (readDiaryRepository.findByUserIdAndAndRecentId(userId, recentDiaryId).isEmpty()) { // 아직 안읽었음
                    readDiaryRepository.save(ReadDiary.builder()
                            .userId(userId)
                            .recentId(recentDiaryId)
                            .build());
                }
            }

            return true;
        }

    }


    // recent diary 가져오기
    @Override
    public List<RecentDiaryResponseDto> selectAllRecentDiary(Long userId) {

        if (followRepository.findAllByFollower(userId).size() == 0) {
            return null;
        }

        List<RecentDiary> recentDiaryList = recentDiaryCustomRepository.selectAllRecentDiaryByFollowing(userId); // 팔로우 하는 사용자의 recentdiary 찾기
        List<RecentDiaryResponseDto> recentDiaryDtoList = new ArrayList<>();
        int i = 0;
        for (RecentDiary recentDiary : recentDiaryList) {

            if (readDiaryRepository.findByUserIdAndAndRecentId(userId, recentDiary.getRecentId()).isEmpty()) {
                recentDiaryDtoList.add(RecentDiaryResponseDto.builder()
                        .diaryId(recentDiary.getDiaryId())
                        .userId(recentDiary.getUserId())
                        .profileImg(recentDiary.getUser().getProfileImg())
                        .nickname(recentDiary.getUser().getNickname())
                        .build());
                i++;
            }
            if (i >= 20) {
                break;
            }

        }

        return recentDiaryDtoList;
    }
}
