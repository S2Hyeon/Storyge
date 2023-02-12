import React, { useState } from "react";
import * as S from "./MyDiaryStyle";
import Spinner from "../../components/spinner/Spinner";
import Emoji from "components/emoji/Emoji";

import angry from "./../../assets/emotionIcons/angry.png";
import aversion from "./../../assets/emotionIcons/aversion.png";
import happy from "./../../assets/emotionIcons/happy.png";
import sad from "./../../assets/emotionIcons/sad.png";
import scared from "./../../assets/emotionIcons/scared.png";
import soso from "./../../assets/emotionIcons/soso.png";
import surprised from "./../../assets/emotionIcons/surprised.png";

import { postDiary } from "api/diary/postDiary";

function Modal({ setModalOpen, diary, content, num }) {
  const [isChecked, setIsChecked] = useState(num);
  const emotionList = [angry, aversion, happy, sad, scared, soso, surprised];

  // // 작성된 일기와 분석 내용 서버에 전송
  async function writeDiary() {
    await postDiary(diary, content);
    // setCommentInputData(""); //임시 주석: 태현
    // setChangedCount(changedCount + 1); //임시주석: 태현
  }

  return (
    <S.Modal>
      {isChecked === 0 ? (
        <S.ModalItems>
          <div>{content}</div>
          <p>우리가 분석한 감정이에요! 😍</p>
          <Emoji emotion={content[0]} thisWidth="30px" />
          <S.ModalBtnDiv>
            <button onClick={writeDiary}>맞워요</button>
            <button onClick={() => setIsChecked(1)}>않이요</button>
          </S.ModalBtnDiv>
        </S.ModalItems>
      ) : isChecked === 1 ? (
        <S.ModalItems>
          <p>그럼 니가 골라보던가 흥 😍</p>
          <S.Row>
            {emotionList.map((emotion) => {
              return <S.EmotionBtn emotion={emotion} key={emotion} />;
            })}
          </S.Row>
          <S.ModalBtnDiv>
            <button onClick={() => setModalOpen(false)}>확인</button>
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
