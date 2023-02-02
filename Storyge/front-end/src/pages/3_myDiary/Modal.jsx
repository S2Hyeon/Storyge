import React, { useState } from "react";
import * as S from "./MyDiaryStyle";

import angry from "./../../assets/emotionIcons/angry.png";
import aversion from "./../../assets/emotionIcons/aversion.png";
import happy from "./../../assets/emotionIcons/happy.png";
import sad from "./../../assets/emotionIcons/sad.png";
import scared from "./../../assets/emotionIcons/scared.png";
import soso from "./../../assets/emotionIcons/soso.png";
import surprised from "./../../assets/emotionIcons/surprised.png";

function Modal({ setModalOpen }) {
  const [isChecked, setIsChecked] = useState(true);
  const emotionList = [angry, aversion, happy, sad, scared, soso, surprised];

  return (
    <S.Modal>
      {isChecked ? (
        <S.ModalItems>
          <p>우리가 분석한 감정이에요! 😍</p>
          <S.ModalBtnDiv>
            <button onClick={() => setModalOpen(false)}>맞워요</button>
            <button onClick={() => setIsChecked((e) => !e)}>않이요</button>
          </S.ModalBtnDiv>
        </S.ModalItems>
      ) : (
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
      )}
    </S.Modal>
  );
}

export default Modal;
