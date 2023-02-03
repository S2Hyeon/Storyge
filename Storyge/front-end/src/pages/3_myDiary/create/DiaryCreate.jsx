import React, { useRef, useState } from "react";
import { useNavigate } from "react-router-dom";
import Clock from "react-live-clock";
import Modal from "../Modal";
import * as S from "../MyDiaryStyle";
import * as A from "../../../styles/index";
import Toggle from "../Toggle";

export default function Creatediary() {
  const navigate = useNavigate();
  const contentRef = useRef();
  const [content, setContent] = useState("");
  const [modalOpen, setModalOpen] = useState(false);

  function onChange(e) {
    setContent(e.target.value);
    if (content.length > 99) {
      alert("일기가 너무 길어요~");
      setContent(content.substr(0, 99));
    }
  }
  function isWritten() {
    if (content.length === 0) {
      alert("일기를 작성하세요~");
    } else if (content.length > 100) {
      console.log("일기가 너무 길다 얘야");
    } else {
      console.log("무사히 작성됨", content.length);
      setModalOpen(true);
      console.log(modalOpen);
    }
  }

  return (
    <>
      <S.Mother>
        <h1>일기 작성 페이지</h1>
        <Clock format={"작성날짜 YYYY.MM.DD 작성시간 HH:mm"} ticking={true} />
        <S.Card backgroundColor="var(--color-white)">
          <S.TextBox
            type="text"
            placeholder="대충 멋진 문구로 글쓰기를 자극하라"
            ref={contentRef}
            value={content}
            onChange={onChange}
          />
          <S.CardFoot height="30px" backgroundColor="var(--color-white)">
            <S.CountDiary>{content.length} / 100</S.CountDiary>
            <Toggle />
          </S.CardFoot>
        </S.Card>
        <div>
          <div>
            <A.longBtnDefault onClick={isWritten}>
              감정분석하기 버튼
            </A.longBtnDefault>
          </div>
          <A.longBtnBorder onClick={() => navigate(-1)}>
            일단 뒤로가기 버튼
          </A.longBtnBorder>
        </div>
      </S.Mother>
      {modalOpen && <Modal setModalOpen={setModalOpen} />}
    </>
  );
}
