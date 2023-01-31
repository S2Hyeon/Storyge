import React from "react";

function Modal({ setModalOpen }) {
  return (
    <div>
      <p>우리가 분석한 감정이에요! 😍</p>
      <button onClick={() => setModalOpen(false)}>맞워요</button>
      <button>않이요</button>
    </div>
  );
}

export default Modal;
