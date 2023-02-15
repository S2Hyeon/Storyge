import React, { useState } from "react";
import * as S from "./ModalStyle";
import Spinner from "../../components/spinner/Spinner";
import Emoji from "components/emoji/Emoji";
import { useNavigate } from "react-router-dom";

import { postDiary } from "api/diary/postDiary";
import { putDiary } from "api/diary/putDiary";

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
  const movePage = useNavigate();
  const [reccomendEmotion, setRecommendEmotion] = useState(
    content && content[0]
  );
  const [isChecked, setIsChecked] = useState(num);
  const emotionList = [
    "angry",
    "aversion",
    "happy",
    "sad",
    "scared",
    "soso",
    "surprised",
  ];
  // // 작성된 일기와 분석 내용 서버에 전송
  async function writeDiary() {
    if (classify === "create") {
      await postDiary(diary, [reccomendEmotion, content[1]], scope);
      movePage(`/diarylist`, { state: { date: new Date() } });
    } else {
      await putDiary(diary, [reccomendEmotion, content[1]], diaryId, scope);
      movePage(`/diarylist`, { state: { date: createdAt } });
    }
  }

  return (
    <S.Modal>
      {isChecked === 0 ? (
        <S.ModalItems>
          <p>우리가 분석한 감정이에요! 😍</p>
          <Emoji emotion={content[0]} thisWidth="30px" />
          <S.ModalBtnDiv>
            <S.NoBtn onClick={() => setIsChecked(1)}>아니에요</S.NoBtn>
            <S.CancelBtn onClick={() => setModalOpen(false)}>취소</S.CancelBtn>
            <S.YesBtn onClick={writeDiary}>맞아요!</S.YesBtn>
          </S.ModalBtnDiv>
        </S.ModalItems>
      ) : isChecked === 1 ? (
        <S.ModalItems>
          <p>어떤 감정을 느끼고 계신가요?</p>
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
              );
            })}
          </S.Row>
          <S.ModalBtnDiv>
            <S.CancelBtn onClick={() => setModalOpen(false)}>취소</S.CancelBtn>
            <S.YesBtn onClick={writeDiary}>등록하기</S.YesBtn>
          </S.ModalBtnDiv>
        </S.ModalItems>
      ) : isChecked === 2 ? (
        <S.ModalItems>
          <Spinner />
        </S.ModalItems>
      ) : null}
    </S.Modal>
  );
}

export default Modal;
