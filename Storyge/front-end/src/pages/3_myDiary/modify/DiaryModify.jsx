import React, { useEffect, useRef, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import Clock from "react-live-clock";
import Modal from "../Modal";
import * as heyhey from "./DiaryModifyStyle";
import * as G from "../../../styles/index";
import { OpenAI } from "../../../openai/OpenAI";
import Switch from "react-switch";
import { GrLock, GrUnlock } from "react-icons/gr";

import { getCount } from "api/diary/getCount";

export default function Modifydiary() {
  const location = useLocation();
  const [already] = useState(location.state.already);
  const navigate = useNavigate();
  const contentRef = useRef();
  const [count, setCount] = useState(0);
  const [content, setContent] = useState(already.diaryContent);
  const [diaryId] = useState(already.diaryId);
  const [modalOpen, setModalOpen] = useState(false);
  const [info, setInfo] = useState(["emotion", "comment"]);
  const [spinner, setSpinner] = useState(false);
  const [checked, setChecked] = useState(already.scope === 0 ? true : false);
  const handleChange = (nextChecked) => {
    setChecked(nextChecked);
    console.log(checked);
  };
  useEffect(() => {
    async function getDiaryCount() {
      const response = await getCount();
      console.log("다이어리 작성 횟수 가져오기");
      setCount(response);
      console.log("다이어리 작성 횟수 : " + count);
    }
    getDiaryCount();
  }, [count]);

  async function getInfo(content, setModalOpen) {
    await OpenAI({ input: content, type: 1 })
      .then((data1) => {
        console.log("then 실행됨", data1);
        OpenAI({ input: data1[1], type: 2 })
          .then((data2) => {
            console.log("번역 실행됨", data2);
            setSpinner(false);
            setModalOpen(true);
            setInfo([data1[0], data2]);
            console.log("셋 인포는 뭔가요?");
            console.log(info[0]);
            console.log(info[1]);
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
      // 일기를 작성 할 수 있는 횟수 검사
      setSpinner(true);
      const test = await getInfo(content, setModalOpen);
      // console.log(test);
      setInfo(test);
    }
  }

  return (
    <>
      <heyhey.container>
        <h1>일기 작성 페이지</h1>
        <Clock format={"작성날짜 YYYY.MM.DD 작성시간 HH:mm"} ticking={true} />
        <heyhey.card backgroundColor="var(--color-white)">
          <heyhey.TextBox
            type="text"
            placeholder="대충 멋진 문구로 글쓰기를 자극하라"
            ref={contentRef}
            value={content}
            onChange={onChange}
          />
          <heyhey.CardFoot height="30px" backgroundColor="var(--color-white)">
            <heyhey.CountDiary>
              {content && content.length} / 100
            </heyhey.CountDiary>
            <Switch
              onChange={handleChange}
              checked={checked}
              offColor="#c0bcbc"
              onColor="#accebc"
              uncheckedIcon={
                <heyhey.Test>
                  <GrUnlock color="#ffffff" />
                </heyhey.Test>
              }
              checkedIcon={
                <heyhey.Test>
                  <GrLock color="#ffffff" />
                </heyhey.Test>
              }
            />
          </heyhey.CardFoot>
        </heyhey.card>
        <div>
          <div>
            <G.longBtnDefault onClick={isWritten}>
              감정분석하기 버튼
            </G.longBtnDefault>
          </div>
          <G.longBtnBorder onClick={() => navigate(-1)}>
            일단 뒤로가기 버튼
          </G.longBtnBorder>
        </div>
      </heyhey.container>
      {modalOpen && (
        <Modal
          setModalOpen={setModalOpen}
          diary={content}
          content={info}
          num={0}
          diaryId={diaryId}
          classify="modify"
          scope={checked ? 0 : 1}
        />
      )}
      {spinner && <Modal setModalOpen={setModalOpen} content={info} num={2} />}
    </>
  );
}
