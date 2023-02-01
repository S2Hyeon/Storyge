import React, { useState } from "react";
import * as S from "./MainStyle";
import * as G from "../../styles";
import newDiaryData from "./NewDiaryData";
import { BsCircleFill } from "react-icons/bs";
import CustomCalendar from "../../components/calender/Calendar";

function Main() {
  let [diary, setDiary] = useState(true);
  let [statistic, setStatistic] = useState(false);

  function switchDiary() {
    setDiary(true);
    setStatistic(false);
    console.log("다이어리로 넘어가기");
  }

  function switchStatistic() {
    setDiary(false);
    setStatistic(true);
    console.log("통계로 넘어가기");
  }

  return (
    <S.All>
      <S.NewDiary>
        {newDiaryData.map((diary) => {
          return <S.Profile profile={diary.imgUrl} key={diary.id} />;
        })}
      </S.NewDiary>
      <G.BodyContainer top="0" bottom="70px" color="true">
        <S.CalendarContainer>
          <S.CalendarBox>
            <CustomCalendar />
          </S.CalendarBox>
          <S.CalendarToggle>
            <S.ToggleOne onClick={() => switchDiary()}>
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
            <S.ToggleOne onClick={() => switchStatistic()}>
              <BsCircleFill
                size={7}
                color={
                  statistic ? "var(--color-primary)" : "var(--color-darkgrey)"
                }
              />
              <span
                style={{
                  color: statistic
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
