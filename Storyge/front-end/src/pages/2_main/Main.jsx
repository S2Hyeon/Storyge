import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import * as S from "./MainStyle";
import * as G from "../../styles";
import newDiaryData from "./NewDiaryData";
// import pieChartData from "./PieChartData";
import { BsCircleFill } from "react-icons/bs";
import CustomCalendar from "../../components/calender/Calendar";
import PieChart from "../../components/chart/PieChart";

function Main() {
  const movePage = useNavigate();

  let [diary, setDiary] = useState(true);
  // let [chartData, setChartData] = useState(pieChartData);

  //새로 업데이트 된 글로 이동!
  function goUpdatedDiary(id, userId) {
    // console.log(id, userId);
    movePage("/otherdiarydetail", { state: { id: id, userId: userId } });
  }

  //달력 보일건지 통계 보일건지 전환하는 함수
  function switchBox() {
    setDiary(!diary);
  }

  return (
    <S.All>
      <S.NewDiary>
        {newDiaryData.map((diary) => {
          return (
            <S.Profile
              profile={diary.imgUrl}
              key={diary.id}
              onClick={() => goUpdatedDiary(diary.id, diary.userId)}
            />
          );
        })}
      </S.NewDiary>
      <G.BodyContainer top="0" bottom="70px" color="true">
        <S.CalendarContainer>
          <S.CalendarBox>
            {diary ? <CustomCalendar /> : <PieChart />}
          </S.CalendarBox>
          <S.CalendarToggle onClick={() => switchBox()}>
            <S.ToggleOne>
              <BsCircleFill
                size={7}
                color={diary ? "var(--color-primary)" : "var(--color-darkgrey)"}
              />
              <span
                style={{
                  color: diary ? "var(--color-black)" : "var(--color-darkgrey)",
                }}
              >
                {" "}
                다이어리
              </span>
            </S.ToggleOne>
            <S.ToggleOne>
              <BsCircleFill
                size={7}
                color={
                  !diary ? "var(--color-primary)" : "var(--color-darkgrey)"
                }
              />
              <span
                style={{
                  color: !diary
                    ? "var(--color-black)"
                    : "var(--color-darkgrey)",
                }}
              >
                {" "}
                통계
              </span>
            </S.ToggleOne>
          </S.CalendarToggle>
        </S.CalendarContainer>
        <S.WiseBox>
          <S.Wise>
            우리가 출발한 곳은 선택할 수 없지만,
            <br />
            그곳에서 어딜 향해 갈지는 선택할 수 있어.
          </S.Wise>
          <S.WiseFrom>영화 &lt;월 플라워&gt; 중</S.WiseFrom>
        </S.WiseBox>
      </G.BodyContainer>
    </S.All>
  );
}

export default Main;
