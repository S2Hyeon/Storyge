import React, { useState, useEffect } from "react";
import * as S from "./MyDiaryStyle";
import { OpenAI } from "../../openai/OpenAI";
import Spinner from "../../components/spinner/Spinner";

import angry from "./../../assets/emotionIcons/angry.png";
import aversion from "./../../assets/emotionIcons/aversion.png";
import happy from "./../../assets/emotionIcons/happy.png";
import sad from "./../../assets/emotionIcons/sad.png";
import scared from "./../../assets/emotionIcons/scared.png";
import soso from "./../../assets/emotionIcons/soso.png";
import surprised from "./../../assets/emotionIcons/surprised.png";

function Modal({ setModalOpen, content }) {
  console.log(content);
  // let data = null;
  const [isChecked, setIsChecked] = useState(0);
  const emotionList = [angry, aversion, happy, sad, scared, soso, surprised];
  // useEffect(() => {
  //   if (data === null) {
  //     setIsChecked(2);
  //   } else {
  //     setIsChecked(0);
  //   }
  // }, []);

  return (
    <S.Modal>
      {isChecked === 0 ? (
        <S.ModalItems>
          <p>우리가 분석한 감정이에요! 😍</p>
          <p>{content}</p>
          <S.ModalBtnDiv>
            <button onClick={() => setModalOpen(false)}>맞워요</button>
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
