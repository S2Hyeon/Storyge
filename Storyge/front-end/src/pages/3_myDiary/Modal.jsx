import React from "react";
import * as S from "./MyDiaryStyle";

function Modal({ setModalOpen }) {
  return (
    <S.Modal>
      <div>
        <p>우리가 분석한 감정이에요! 😍</p>
        <button onClick={() => setModalOpen(false)}>맞워요</button>
        <button>않이요</button>
      </div>
    </S.Modal>
  );
}

export default Modal;
