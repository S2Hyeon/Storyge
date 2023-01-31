package com.example.project.diary.model.service;

import com.example.project.diary.model.dto.DiaryDto;
import com.example.project.diary.model.dto.DiaryUpdateParam;
import com.example.project.diary.model.entity.Diary;
import com.example.project.diary.model.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class DiaryServiceImpl implements DiaryService{

    private final DiaryRepository diaryRepository;
    @Override
    public void insertDiary(DiaryDto diaryDto) {
        diaryRepository.save(toEntity(diaryDto));
    }


    @Override
    public Optional<DiaryDto> selectOneDiary(Long diaryId) {
        return Optional.ofNullable(toDto(diaryRepository.findById(diaryId).orElseThrow()));
    }

    public List<DiaryDto> selectDailyDiaries(Long userId, Date date) {
        return diaryRepository.findByIdAndCreatedAtContaining(userId, date);
    }

    /*
    만약 일기의 내용이 변경되지 않았다면 이모티콘과 공개여부 설정만 변경하고
    일기의 내용이 변경되었다면 이모티콘과 일기내용, 공개여부, 감정분석 결과 모두를 변경한다.
        => 일기 수정 횟수의 경우 일기의 내용이 변경되었다면 1로 변경되어야하기 때문에 entity에서 값을 변경할 때 설정해준다.
     */
    @Override
    public void updateDiary(DiaryUpdateParam param) {
        Diary diary = diaryRepository.findById(param.getDiaryId()).orElseThrow();
        if(diary.getDiaryContent().equals(param.getDiaryContent())) {
            diary.updateDiary(param.getEmoticonId(),
                                param.getScope());
        }
        else {
            diary.updateDiaryContent(param.getEmoticonId(),
                                        param.getDiaryContent(),
                                        param.getScope(),
                                        param.getAnalizedResult());
        }

    }

    @Override
    public void deleteDiary(Long diaryId) {
        diaryRepository.deleteById(diaryId);
    }
}
