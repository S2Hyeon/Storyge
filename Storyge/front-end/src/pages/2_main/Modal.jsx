import React from "react";
import * as S from "./MyDiaryStyle";

function Modal({ setIsGloomy }) {
  return (
    <S.Modal>
      <S.ModalItems>당신은 우울해 보입니다.</S.ModalItems>
      <button onClick={() => setIsGloomy(false)}>확인</button>
    </S.Modal>
  );
}

export default Modal;
