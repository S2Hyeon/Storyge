import React, { useRef, useState } from "react";
import { useNavigate } from "react-router-dom";
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
      <S.AutoBtn onClick={decreaseDate}>
        <BsFillCaretLeftFill />
      </S.AutoBtn>
      <div>{headDate}</div>
      <S.AutoBtn onClick={increaseDate}>
        <BsFillCaretRightFill />
      </S.AutoBtn>
    </A.BodyContainer>
  );
}
