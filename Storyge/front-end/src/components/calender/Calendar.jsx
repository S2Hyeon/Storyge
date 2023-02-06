import dayjs from "dayjs";
import React, { useState } from "react";
import Calendar from "react-calendar";
import "./Calendar.css"; // css import
import datas from "./CalendarData";
import Emoji from "components/emoji/Emoji";

import angry from "../../assets/emotionIcons/angry.png";
import aversion from "../../assets/emotionIcons/aversion.png";
import happy from "../../assets/emotionIcons/happy.png";
import sad from "../../assets/emotionIcons/sad.png";
import scared from "../../assets/emotionIcons/scared.png";
import soso from "../../assets/emotionIcons/soso.png";
import surprised from "../../assets/emotionIcons/surprised.png";
import noEmotion from "../../assets/emotionIcons/noEmotion.png";

function CustomCalendar() {
  const [emotionData] = useState(datas);

  function displayEmotion(props) {
    if (props != null) {
      if (props.emotion === "angry") {
        return <img src={angry} alt="angry" width="80%" />;
      } else if (props.emotion === "aversion") {
        return <img src={aversion} alt="aversion" width="80%" />;
      } else if (props.emotion === "happy") {
        return <img src={happy} alt="happy" width="80%" />;
      } else if (props.emotion === "sad") {
        return <img src={sad} alt="sad" width="80%" />;
      } else if (props.emotion === "scared") {
        return <img src={scared} alt="scared" width="80%" />;
      } else if (props.emotion === "soso") {
        return <img src={soso} alt="soso" width="80%" />;
      } else if (props.emotion === "surprised") {
        return <img src={surprised} alt="surprised" width="80%" />;
      }
    } else {
      return <img src={noEmotion} alt="noemotion" width="80%" />;
    }
  }

  return (
    <>
      <Emoji emotion="happy" />
      <Calendar
        calendarType="US"
        showNeighboringMonth={false} //  이전, 이후 달의 날짜는 보이지 않도록 설정
        formatDay={(locale, date) => dayjs(date).format("D")}
        tileContent={({ date, view }) => {
          let chosenData = emotionData.find(
            ({ feelDate }) => feelDate === dayjs(date).format("YYYY-MM-DD")
          );
          console.log(chosenData);
          // return <Emoji emotion={chosenData.emotion} />;
          return displayEmotion(chosenData);
        }}
      />
    </>
  );
}

export default CustomCalendar;
