import React, { useState } from "react";
import * as S from "./MyDiaryStyle";
import * as A from "./../../styles/index";
import { BsFillCaretLeftFill, BsFillCaretRightFill } from "react-icons/bs";

export default function Diarylist() {
  const [dateInfo, setDateInfo] = useState(new Date());
  const headDate = `${dateInfo.getFullYear()}.${
    dateInfo.getMonth() + 1
  }.${dateInfo.getDate()}`;

  const decreaseDate = () => {
    setDateInfo(
      new Date(
        dateInfo.getFullYear(),
        dateInfo.getMonth(),
        dateInfo.getDate() - 1
      )
    );
  };
  const increaseDate = () => {
    setDateInfo(
      new Date(
        dateInfo.getFullYear(),
        dateInfo.getMonth(),
        dateInfo.getDate() + 1
      )
    );
  };
  return (
    <A.BodyContainer>
      <S.DiaryHeader>
        <S.AutoBtn onClick={decreaseDate}>
          <BsFillCaretLeftFill />
        </S.AutoBtn>
        <S.DateInfo>{headDate}</S.DateInfo>
        <S.AutoBtn onClick={increaseDate}>
          <BsFillCaretRightFill />
        </S.AutoBtn>
      </S.DiaryHeader>
      {S.data.map((diary) => {
        return (
          <S.DiaryBox key={diary.id}>
            <S.Emotion emotion={diary.img} />
            <S.Col>
              <div>{diary.time}</div>
              <S.Content>{diary.content}</S.Content>
            </S.Col>
          </S.DiaryBox>
        );
      })}
    </A.BodyContainer>
  );
}
