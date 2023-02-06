import React from "react";

// 이미지 임포트
import angry from "assets/emotionIcons/angry.png";
import aversion from "assets/emotionIcons/aversion.png";
import happy from "assets/emotionIcons/happy.png";
import sad from "assets/emotionIcons/sad.png";
import scared from "assets/emotionIcons/scared.png";
import soso from "assets/emotionIcons/soso.png";
import surprised from "assets/emotionIcons/surprised.png";
import noEmotion from "assets/emotionIcons/noEmotion.png";

function Emoji(props) {
  let thisWidth = props.thisWidth; //props로 받은 너비값

  if (props.emotion == "noemotion") {
    return <img src={noEmotion} alt="noemotion" width={thisWidth} />;
  } else {
    if (props.emotion === "angry") {
      return <img src={angry} alt="angry" width={thisWidth} />;
    } else if (props.emotion === "aversion") {
      return <img src={aversion} alt="aversion" width={thisWidth} />;
    } else if (props.emotion === "happy") {
      return <img src={happy} alt="happy" width={thisWidth} />;
    } else if (props.emotion === "sad") {
      return <img src={sad} alt="sad" width={thisWidth} />;
    } else if (props.emotion === "scared") {
      return <img src={scared} alt="scared" width={thisWidth} />;
    } else if (props.emotion === "soso") {
      return <img src={soso} alt="soso" width={thisWidth} />;
    } else if (props.emotion === "surprised") {
      return <img src={surprised} alt="surprised" width={thisWidth} />;
    }
  }
}

export default Emoji;
