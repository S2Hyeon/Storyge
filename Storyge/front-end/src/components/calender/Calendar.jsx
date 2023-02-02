import dayjs from "dayjs";
import React, { useState } from "react";
import Calendar from "react-calendar";
import { useQuery } from "react-query";
import "./Calendar.css"; // css import
import datas from "./CalendarData";
// import { EmotionImg } from "./EmotionIcon";

import angry from "../../assets/emotionIcons/angry.png";
import aversion from "../../assets/emotionIcons/aversion.png";
import happy from "../../assets/emotionIcons/happy.png";
import sad from "../../assets/emotionIcons/sad.png";
import scared from "../../assets/emotionIcons/scared.png";
import soso from "../../assets/emotionIcons/soso.png";
import surprised from "../../assets/emotionIcons/surprised.png";
import noEmotion from "../../assets/emotionIcons/noEmotion.png";

function CustomCalendar() {
  const [emotionData, setEmotionData] = useState(datas);

  function displayEmotion(props) {
    if (props != null) {
      if (props.emotion === "angry") {
        return <img src={angry} width="80%" />;
      } else if (props.emotion === "aversion") {
        return <img src={aversion} width="80%" />;
      } else if (props.emotion === "happy") {
        return <img src={happy} width="80%" />;
      } else if (props.emotion === "sad") {
        return <img src={sad} width="80%" />;
      } else if (props.emotion === "scared") {
        return <img src={scared} width="80%" />;
      } else if (props.emotion === "soso") {
        return <img src={soso} width="80%" />;
      } else if (props.emotion === "surprised") {
        return <img src={surprised} width="80%" />;
      }
    } else {
      return <img src={noEmotion} width="80%" />;
    }
  }

  return (
    <Calendar
      calendarType="US"
      showNeighboringMonth={false} //  이전, 이후 달의 날짜는 보이지 않도록 설정
      formatDay={(locale, date) => dayjs(date).format("D")}
      tileContent={({ date, view }) => {
        let chosenData = emotionData.find(
          ({ feelDate }) => feelDate === dayjs(date).format("YYYY-MM-DD")
        );
        return displayEmotion(chosenData);
      }}
    />
  );
}

export default CustomCalendar;
