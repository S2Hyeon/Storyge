import dayjs from "dayjs";
import React, { useState } from "react";
import Calendar from "react-calendar";
import "./Calendar.css"; // css import
import datas from "./CalendarData";
import Emoji from "components/emoji/Emoji";
import { useNavigate } from "react-router";

function CustomCalendar() {
  const [emotionData] = useState(datas);
  const movePage = useNavigate();

  //1. 사용자가 자신의 글을 보는 경우
  function goDiaryList(date) {
    movePage("/diarylist", { state: { date: date } });
  }

  //2. 다른 사용자의 글을 보는 경우(== 다른 사용자 달력에서 클릭할 경우)

  return (
    <Calendar
      calendarType="US"
      showNeighboringMonth={false} //  이전, 이후 달의 날짜는 보이지 않도록 설정
      formatDay={(locale, date) => dayjs(date).format("D")}
      onClickDay={(date) => goDiaryList(date)}
      tileContent={({ date }) => {
        let chosenData = emotionData.find(
          ({ feelDate }) => feelDate === dayjs(date).format("YYYY-MM-DD")
        );

        //해당 일자에 데이터가 있다면 그 감정을 props로 넘김
        if (chosenData != null) {
          return <Emoji emotion={chosenData.emotion} thisWidth="80%" />;
        }
        //해당 일자에 데이터가 없다면 noemotion을 props로 넘기기
        else {
          return <Emoji emotion="noemotion" thisWidth="80%" />;
        }
      }}
    />
  );
}

export default CustomCalendar;
