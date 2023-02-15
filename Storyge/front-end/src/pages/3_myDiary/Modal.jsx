import React, { useState } from 'react'
import * as S from './MyDiaryStyle'
import Spinner from '../../components/spinner/Spinner'
import Emoji from 'components/emoji/Emoji'
import { useNavigate } from 'react-router-dom'

import { postDiary } from 'api/diary/postDiary'
import { putDiary } from 'api/diary/putDiary'

function Modal({
  diary,
  content,
  num,
  classify,
  diaryId,
  scope,
  setModalOpen,
  createdAt,
}) {
  const movePage = useNavigate()
  const [reccomendEmotion, setRecommendEmotion] = useState(
    content && content[0],
  )
  const [isChecked, setIsChecked] = useState(num)
  const emotionList = [
    'angry',
    'aversion',
    'happy',
    'sad',
    'scared',
    'soso',
    'surprised',
  ]
  // // 작성된 일기와 분석 내용 서버에 전송
  async function writeDiary() {
    // const curDate = dayjs(new Date()).format("YYYY-MM-DD");
    if (classify === 'create') {
      await postDiary(diary, [reccomendEmotion, content[1]], scope)
      movePage(`/diarylist`, { state: { date: new Date() } })
    } else {
      await putDiary(diary, [reccomendEmotion, content[1]], diaryId, scope)
      movePage(`/diarylist`, { state: { date: createdAt } })
    }
  }

  return (
    <S.Modal>
      {isChecked === 0 ? (
        <S.ModalItems>
          <p>우리가 분석한 감정이에요! 😍</p>
          <Emoji emotion={content[0]} thisWidth="30px" />
          <S.ModalBtnDiv>
            <S.ModalBtn color="#84b9c0" onClick={writeDiary}>
              <S.ModalText>맞아요</S.ModalText>
            </S.ModalBtn>
            <S.ModalBtn color="#cfcece" onClick={() => setIsChecked(1)}>
              <S.ModalText>아니요</S.ModalText>
            </S.ModalBtn>
          </S.ModalBtnDiv>
        </S.ModalItems>
      ) : isChecked === 1 ? (
        <S.ModalItems>
          <p>감정을 선택해주세요</p>
          <S.Row>
            {emotionList.map((emotion) => {
              return (
                <div key={emotion}>
                  {emotion === reccomendEmotion ? (
                    <S.test onClick={(e) => setRecommendEmotion(e.target.alt)}>
                      <Emoji emotion={emotion} thisWidth="30px" />
                    </S.test>
                  ) : (
                    <S.EmotionBtn
                      onClick={(e) => setRecommendEmotion(e.target.alt)}
                    >
                      <Emoji emotion={emotion} thisWidth="30px" />
                    </S.EmotionBtn>
                  )}
                </div>
              )
            })}
          </S.Row>
          <S.ModalBtnDiv>
            <S.ModalBtn color="var(--color-primary)" onClick={writeDiary}>
              <S.ModalText>확인</S.ModalText>
            </S.ModalBtn>
          </S.ModalBtnDiv>
        </S.ModalItems>
      ) : isChecked === 2 ? (
        <S.ModalItems>
          <Spinner />
        </S.ModalItems>
      ) : null}
    </S.Modal>
  )
}

export default Modal
