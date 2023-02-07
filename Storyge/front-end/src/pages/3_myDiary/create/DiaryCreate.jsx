import React, { useRef, useState, Suspense } from "react";
import { useNavigate } from "react-router-dom";
import Clock from "react-live-clock";
// import Modal from "../Modal";
import * as S from "../MyDiaryStyle";
import * as A from "../../../styles/index";
import Toggle from "../Toggle";
import Spinner from "../../../components/spinner/Spinner";
import { OpenAI } from "../../../openai/OpenAI";

// async function getInfo(content, setModalOpen) {
//   const data1 = await OpenAI({ input: content, type: 1 });
//   const data2 = await OpenAI({ input: data1[1], type: 2 });
//   console.log(data1, data2);
//   setModalOpen(true);
//   return [data1[0], data2];
// }
export default function Creatediary() {
  const navigate = useNavigate();
  const contentRef = useRef();
  const [content, setContent] = useState("");
  const [modalOpen, setModalOpen] = useState(false);
  const [info, setInfo] = useState([]);
  async function getInfo(content, setModalOpen) {
    await OpenAI({ input: content, type: 1 })
      .then((data1) => {
        console.log("then 실행됨", data1);
        OpenAI({ input: data1[1], type: 2 })
          .then((data2) => {
            console.log("번역 실행됨", data2);
            setModalOpen(true);
            setInfo([data1[0], data2]);
          })
          .catch((err) => {
            console.log(err);
          });
      })
      .catch((err) => {
        console.log(err);
      });
  }

  function onChange(e) {
    setContent(e.target.value);
    if (content.length > 99) {
      alert("일기가 너무 길어요~");
      setContent(content.substr(0, 99));
    }
  }
  async function isWritten() {
    if (content.length === 0) {
      alert("일기를 작성하세요~");
    } else if (content.length > 100) {
    } else {
      const test = await getInfo(content, setModalOpen);
      console.log(test);
      setInfo(test);
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
      {modalOpen && (
        <S.Modal>
          <p>{info}</p>
        </S.Modal>
      )}
    </>
  );
}
