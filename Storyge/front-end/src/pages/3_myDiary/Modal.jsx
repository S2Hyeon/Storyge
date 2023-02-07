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
  // const [isChecked, setIsChecked] = useState(true);
  const [isChecked, setIsChecked] = useState(2);
  const emotionList = [angry, aversion, happy, sad, scared, soso, surprised];
  const [result, setResult] = useState("");

  // setResult(OpenAI({ input: content, type: 1 }));

  useEffect(() => {
    setResult(OpenAI({ input: content, type: 1 }));
  }, []);

  // useEffect(() => {
  //   console.log(result);
  //   if (result) {
  //     setIsChecked(0);
  //   }
  // }, [result]);

  setTimeout(() => {
    setIsChecked(0);
    console.log(result);
  }, 3000);

  return (
    <S.Modal>
      {isChecked === 0 ? (
        <S.ModalItems>
          <p>우리가 분석한 감정이에요! 😍</p>
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
