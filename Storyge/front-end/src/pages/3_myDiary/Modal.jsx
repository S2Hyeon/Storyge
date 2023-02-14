import React, { useState } from "react";
import * as S from "./MyDiaryStyle";
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
    // const curDate = dayjs(new Date()).format("YYYY-MM-DD");
    console.log(diary, [reccomendEmotion, content[1]]);
    if (classify === "create") {
      await postDiary(diary, [reccomendEmotion, content[1]], scope);
    } else {
      await putDiary(diary, [reccomendEmotion, content[1]], diaryId, scope);
    }
    movePage(`/diarylist`, { state: { date: new Date() } });
  }

  return (
    <S.Modal>
      <button
        onClick={() => {
          setModalOpen(false);
        }}
      >
        X
      </button>
      {isChecked === 0 ? (
        <S.ModalItems>
          <p>우리가 분석한 감정이에요! 😍</p>
          <Emoji emotion={content[0]} thisWidth="30px" />
          <S.ModalBtnDiv>
            <button onClick={writeDiary}>맞워요</button>
            <button onClick={() => setIsChecked(1)}>않이요</button>
          </S.ModalBtnDiv>
        </S.ModalItems>
      ) : isChecked === 1 ? (
        <S.ModalItems>
          <p>그럼 니가 골라보던가 흥 😡</p>
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
            <button onClick={writeDiary}>확인</button>
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
