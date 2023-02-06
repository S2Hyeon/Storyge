import React, { useState } from "react";
import data from "./DiaryListData";
import * as G from "../../../styles/index";
import * as S from "./DiaryListStyle";
import { TbChevronLeft, TbChevronRight } from "react-icons/tb";
import { useLocation, useNavigate } from "react-router";

export default function DiaryList() {
  const location = useLocation();
  const movePage = useNavigate();

  const [dateInfo, setDateInfo] = useState(location.state.date);

  function goDiaryDetail(id) {
    movePage("/diary", { state: { id: id } });
  }

  //년월일 표시
  const headDate = `${dateInfo.getFullYear()}.${
    dateInfo.getMonth() + 1
  }.${dateInfo.getDate()}`;

  //일자 감소
  const decreaseDate = () => {
    setDateInfo(
      new Date(
        dateInfo.getFullYear(),
        dateInfo.getMonth(),
        dateInfo.getDate() - 1
      )
    );
  };

  //일자
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
    // 날짜 표시 및 날짜이동 부분
    <G.BodyContainer>
      <S.DateContainer>
        <S.ArrowBtn onClick={decreaseDate}>
          <TbChevronLeft color="var(--color-primary)" />
        </S.ArrowBtn>
        <S.DateInfo>{headDate}</S.DateInfo>
        <S.ArrowBtn onClick={increaseDate}>
          <TbChevronRight color="var(--color-primary)" />
        </S.ArrowBtn>
      </S.DateContainer>

      {/* 리스트 부분 */}
      {data.map((data) => {
        return (
          <S.ListBox key={data.id} onClick={() => goDiaryDetail(data.id)}>
            <S.Emotion emotion={data.img} />
            <S.TimeSummaryContainer>
              <S.Time>{data.time}</S.Time>
              <S.Summary>{data.content}</S.Summary>
            </S.TimeSummaryContainer>
          </S.ListBox>
        );
      })}
    </G.BodyContainer>
  );
}
