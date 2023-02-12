import React, { useEffect, useState } from "react";
import * as G from "../../../styles/index";
import * as S from "./DiaryListStyle";
import { TbChevronLeft, TbChevronRight } from "react-icons/tb";
import { useLocation, useNavigate } from "react-router";
import { getMyDiaryList } from "api/diary/getMyDiaryList";
import Emoji from "components/emoji/Emoji";
import dayjs from "dayjs";
import { getOtherDiaryList } from "api/diary/getOtherDiaryList";
import { TbLock } from "react-icons/tb";

export default function DiaryList() {
  const location = useLocation();
  const movePage = useNavigate();
  const otherUserId = location.state.otherId;
  console.log(otherUserId);

  //넘어온 날짜 값
  const [dateInfo, setDateInfo] = useState(location.state.date);

  //해당 날짜의 내 일기 목록들
  const [diaryListData, setDiaryListData] = useState([]);

  console.log("선택한 날짜: ", dayjs(dateInfo).format("YYYY-MM-DD"));

  useEffect(() => {
    async function getAndSetDiaryList() {
      if (otherUserId == null) {
        const response = await getMyDiaryList(
          dayjs(dateInfo).format("YYYY-MM-DD")
        );
        setDiaryListData(response);
      } else {
        //다른 사람의 일기리스트라면
        const response = await getOtherDiaryList(
          dayjs(dateInfo).format("YYYY-MM-DD"),
          otherUserId
        );
        setDiaryListData(response);
      }
    }
    getAndSetDiaryList();
  }, [dateInfo]);

  //내 일기 상세 조회 페이지로 이동
  function goDiaryDetail(diaryId) {
    movePage("/diary", { state: { diaryId: diaryId } });
  }

  //년월일 표시
  const headDate = `${dateInfo.getFullYear()}.${dateInfo.getMonth() +
    1}.${dateInfo.getDate()}`;

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

  //일자 증가
  const increaseDate = () => {
    setDateInfo(
      new Date(
        dateInfo.getFullYear(),
        dateInfo.getMonth(),
        dateInfo.getDate() + 1
      )
    );
  };

  let showDiaryData = []; //실제로 보여줄 일기 목록들
  //다른 사람의 일기라면
  if (otherUserId != null) {
    diaryListData.map((data) => {
      //보여줄 리스트에 공개되어있는것만 넣기
      if (data.scope === 1) showDiaryData.push(data);
    });
  }
  //나의 일기라면
  else {
    //그냥 다 넣기
    showDiaryData = diaryListData;
  }
  console.log(showDiaryData);

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
      {/* 나의 일기를 보는거라면 (otherUserId가 Null임)*/}

      {showDiaryData.length === 0 ? (
        <div>보여드릴 일기가 없어요 🥲</div>
      ) : (
        showDiaryData.map((data, index) => {
          return (
            <S.ListBox key={index} onClick={() => goDiaryDetail(data.diaryId)}>
              <Emoji emotion={data.emoticonName} thisWidth="13%" />
              <S.TimeSummaryContainer>
                <S.Time>
                  {dayjs(data.createdAt).format("HH:mm")}{" "}
                  {data.scope === 0 ? <TbLock /> : null}
                </S.Time>
                <S.Summary>{data.diaryContent}</S.Summary>
              </S.TimeSummaryContainer>
            </S.ListBox>
          );
        })
      )}
    </G.BodyContainer>
  );
}
