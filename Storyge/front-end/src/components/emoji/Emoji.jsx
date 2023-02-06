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
  if (props != null) {
    if (props.emotion === "angry") {
      return (
        <img
          src={angry}
          alt="angry"
          width={(props) => (props.width ? props.width : "100%")}
        />
      );
    } else if (props.emotion === "aversion") {
      return (
        <img
          src={aversion}
          alt="aversion"
          width={(props) => (props.width ? props.width : "100%")}
        />
      );
    } else if (props.emotion === "happy") {
      return (
        <img
          src={happy}
          alt="happy"
          width={(props) => (props.width ? props.width : "100%")}
        />
      );
    } else if (props.emotion === "sad") {
      return (
        <img
          src={sad}
          alt="sad"
          width={(props) => (props.width ? props.width : "100%")}
        />
      );
    } else if (props.emotion === "scared") {
      return (
        <img
          src={scared}
          alt="scared"
          width={(props) => (props.width ? props.width : "100%")}
        />
      );
    } else if (props.emotion === "soso") {
      return (
        <img
          src={soso}
          alt="soso"
          width={(props) => (props.width ? props.width : "100%")}
        />
      );
    } else if (props.emotion === "surprised") {
      return (
        <img
          src={surprised}
          alt="surprised"
          width={(props) => (props.width ? props.width : "100%")}
        />
      );
    }
  } else {
    return (
      <img
        src={noEmotion}
        alt="noemotion"
        width={(props) => (props.width ? props.width : "100%")}
      />
    );
  }
}

export default Emoji;
