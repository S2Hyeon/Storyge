import dayjs from "dayjs";
import React, { useState, useEffect } from "react";
import Calendar from "react-calendar";
import "./Calendar.css"; // css import
import Emoji from "components/emoji/Emoji";
import { useNavigate } from "react-router";
import { getMyCalendar } from "api/calendar/getMyCalendar";
import { getOtherCalendar } from "api/calendar/getOtherCalendar";

function CustomCalendar(props) {
  const movePage = useNavigate();

  //-100 이라면 자기 자신이 보는 것. 그 외에는 다른 사용자의 userId
  const userId = props.userId;
  // const [month] = useState(dayjs(new Date()).format("YYYY-MM-01")); //2023-02-01 형식으로 저장
  const now = new Date();
  function lastmonth() {
    const year = now.getFullYear();
    const num = now.getMonth();
    if (num === 0) { 
      return `${year - 1}-12-01`;
    } else if (num === 10 || num === 11) {
      return `${year}-${num}-01`;
    } else {
      return `${year}-0${num}-01`;
    }
  }

  const [monthEmotion, setMonthEmotion] = useState([]); //해당 달의 감정들 저장
  const [curDate, setCurDate] = useState(new Date());

  useEffect(() => {
    const month = dayjs(curDate).format("YYYY-MM-01");
    async function getAndSetCalendar() {
      if (userId < 0) {
        const response = await getMyCalendar(month);
        setMonthEmotion(response);
        if (dayjs(now).format("YYYY-MM-01") === month) {
          const response2 = await getMyCalendar(lastmonth());
          const newArr = response2 ? [...response2, ...response] : response;
          if (newArr.length >= 14) {
            const i = newArr.length;
            if (
              newArr[i - 1].emoticonName === "sad" &&
              newArr[i - 2].emoticonName === "sad" &&
              newArr[i - 3].emoticonName === "sad" &&
              newArr[i - 4].emoticonName === "sad" &&
              newArr[i - 5].emoticonName === "sad" &&
              newArr[i - 6].emoticonName === "sad" &&
              newArr[i - 7].emoticonName === "sad" &&
              newArr[i - 8].emoticonName === "sad" &&
              newArr[i - 9].emoticonName === "sad" &&
              newArr[i - 10].emoticonName === "sad" &&
              newArr[i - 11].emoticonName === "sad" &&
              newArr[i - 12].emoticonName === "sad" &&
              newArr[i - 13].emoticonName === "sad" &&
              newArr[i - 14].emoticonName === "sad"
            ) {
              props.setIsGloomy(true);
            }
          }
        }
      } else {
        const response = await getOtherCalendar(month, userId);
        setMonthEmotion(response);
      }
    }
    getAndSetCalendar();
  }, [curDate]);

  function goDiaryList(date) {
    //1. 지금 달력이 사용자라면 사용자 일기 목록 페이지로 이동
    if (userId < 0) {
      movePage("/diarylist", { state: { date: date } });
    }
    //2. 지금 달력이 다른 사용자라면 다른 사용자 일기 목록 페이지로 이동
    else {
      movePage("/diarylist", {
        state: { date: date, otherId: userId, nickname: props.nickname },
      });
    }
  }

  return (
    <Calendar
      onActiveStartDateChange={(e) => {
        setCurDate(e.activeStartDate);
      }}
      minDetail="month"
      calendarType="US"
      showNeighboringMonth={false} //  이전, 이후 달의 날짜는 보이지 않도록 설정
      formatDay={(locale, date) => dayjs(date).format("D")}
      onClickDay={(date) => {
        if (curDate >= date) {
          goDiaryList(date);
        }
      }}
      tileContent={({ date }) => {
        // getMonth(date);
        let chosenData;
        if (monthEmotion.length !== 0) {
          chosenData = monthEmotion.find(
            ({ createdAt }) => createdAt === dayjs(date).format("YYYY-MM-DD")
          );
        }

        //해당 일자에 데이터가 있다면 그 감정을 props로 넘김
        if (chosenData != null) {
          return <Emoji emotion={chosenData.emoticonName} thisWidth="80%" />;
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
